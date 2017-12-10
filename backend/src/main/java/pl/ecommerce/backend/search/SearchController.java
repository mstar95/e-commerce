package pl.ecommerce.backend.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.search.dto.SaleNameDto;
import pl.ecommerce.backend.search.query.QuerySale;
import pl.ecommerce.backend.search.query.QuerySaleRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final QuerySaleRepository querySaleRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<SaleNameDto>> findNameByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(querySaleRepository.findByNameStartsWith(name));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<QuerySale>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(querySaleRepository.findByName(name));
    }

}
