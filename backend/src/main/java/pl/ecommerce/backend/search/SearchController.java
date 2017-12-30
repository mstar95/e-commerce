package pl.ecommerce.backend.search;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.completion.Completion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.search.dto.SaleNameDto;
import pl.ecommerce.backend.search.query.ElasticSearchQuerySale;
import pl.ecommerce.backend.search.query.ElasticSearchQuerySaleRepository;
import pl.ecommerce.backend.search.query.QuerySale;
import pl.ecommerce.backend.search.query.QuerySaleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final QuerySaleRepository querySaleRepository;
    private final ElasticSearchQuerySaleRepository elasticSearchQuerySaleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

 /*   @GetMapping("/{name}")
    public ResponseEntity<List<SaleNameDto>> findNameByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(querySaleRepository.findByNameStartsWith(name));
    }
*/
    @GetMapping("/{name}")
    public ResponseEntity<List<String>> findNameByName(@PathVariable("name") String name) {
        SuggestionBuilder completionSuggestionFuzzyBuilder = SuggestBuilders.completionSuggestion("completion")
                .text(name);
        final SearchResponse suggestResponse = elasticsearchTemplate.suggest(new SuggestBuilder()
                .addSuggestion("test-suggest",completionSuggestionFuzzyBuilder), ElasticSearchQuerySale.class);
        CompletionSuggestion completionSuggestion = suggestResponse.getSuggest().getSuggestion("test-suggest");

        return ResponseEntity.ok(completionSuggestion.getEntries().get(0).getOptions().stream()
                .map(option -> option.getText().string())
        .collect(Collectors.toList()));
    }

    @GetMapping("/sale/{name}")
    public ResponseEntity<List<QuerySale>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(querySaleRepository.findByName(name));
    }

    @GetMapping("/sale/delete")
    public void findByName() {
        elasticSearchQuerySaleRepository.deleteAll();
    }

}
