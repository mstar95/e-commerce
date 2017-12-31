package pl.ecommerce.backend.search.query;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ecommerce.backend.search.dto.SaleNameDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ElasticSearchHystrixQueryService {
    private final QuerySaleRepository querySaleRepository;
    private final ElasticSearchQuerySaleRepository elasticSearchQuerySaleRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @HystrixCommand(fallbackMethod = "autocompleteFailure")
    public List<SaleNameDto> autocomplete(String name) {
        SuggestionBuilder completionSuggestionFuzzyBuilder = SuggestBuilders.completionSuggestion("completion")
                .text(name);
        final SearchResponse suggestResponse = elasticsearchTemplate.suggest(new SuggestBuilder()
                .addSuggestion("test-suggest", completionSuggestionFuzzyBuilder), ElasticSearchQuerySale.class);
        CompletionSuggestion completionSuggestion = suggestResponse.getSuggest().getSuggestion("test-suggest");

        return completionSuggestion.getEntries().get(0).getOptions().stream()
                .map(option -> option.getText().string())
                .map(SaleNameDto::new)
                .collect(Collectors.toList());
    }

    private List<SaleNameDto> autocompleteFailure(String name, Throwable t) {
        return querySaleRepository.findByNameStartsWith(name);
    }


    public List<QuerySale> findByName(String name) {
        return elasticSearchQuerySaleRepository.findByNameStartsWithOrDescriptionStartsWith(name, name).stream()
                .map(sale -> new QuerySale(sale.getEntityId(),sale.getName(), sale.getImageId(), sale.getPrice(),
                       sale.getCreated(),sale.getDeadline(), sale.isBuyNow())).collect(Collectors.toList());
    }

    private List<QuerySale> findByNameFailure(String name) {
        return querySaleRepository.findByName(name);
    }
}
