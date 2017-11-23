package pl.ecommerce.backend.sale;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.query.QuerySaleRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleController {

    private final QuerySaleRepository querySaleRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SaleOutDto>> all() {
      /*  return Try.of(querySaleRepository::findAll)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null)); */
          return Try.of(() -> Collections.singletonList(SaleOutDto.builder().build()))
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }
}
