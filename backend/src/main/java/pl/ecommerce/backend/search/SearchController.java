package pl.ecommerce.backend.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.search.dto.SaleNameDto;
import pl.ecommerce.backend.search.query.ElasticSearchHystrixQueryService;
import pl.ecommerce.backend.search.query.ElasticSearchQuerySaleRepository;
import pl.ecommerce.backend.search.query.QuerySale;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final ElasticSearchHystrixQueryService elasticSearchHystrixQueryService;
    private final ElasticSearchQuerySaleRepository elasticSearchQuerySaleRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<SaleNameDto>> autocomplete(@PathVariable("name") String name) {
        return ResponseEntity.ok(elasticSearchHystrixQueryService.autocomplete(name));
    }

    @GetMapping("/sale/{name}")
    public ResponseEntity<List<QuerySale>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(elasticSearchHystrixQueryService.findByName(name));
    }

    @GetMapping("/sale/delete")
    public void findByName() {
        elasticSearchQuerySaleRepository.deleteAll();
    }

}
