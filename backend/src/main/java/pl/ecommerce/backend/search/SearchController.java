package pl.ecommerce.backend.search;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.search.domain.SearchFacade;

import java.util.List;


@RequiredArgsConstructor
@RestController("/search")
public class SearchController {

    private final SearchFacade searchFacade;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SaleOutDto>> all() {
        return Try.of(searchFacade::getAuctionsAndSales)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }

}
