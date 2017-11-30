package pl.ecommerce.backend.sale;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.query.QuerySale;
import pl.ecommerce.backend.sale.query.QuerySaleRepository;
import pl.ecommerce.backend.user.dto.CreateUserDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final QuerySaleRepository querySaleRepository;
    private final SaleFacade saleFacade;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SaleOutDto>> all() {
      /*  return Try.of(querySaleRepository::findAll)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null)); */
          return Try.of(() -> Collections.singletonList(SaleOutDto.builder().build()))
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Long> create(@RequestBody CreateSaleDto createSaleDto) {
        createSaleDto.setImage(new byte[1]);
        return ResponseEntity.ok(saleFacade.createSale(createSaleDto));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<QuerySale> get(@PathVariable("id") long id) {
       return Try.of(() -> querySaleRepository.findById(id))
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }
}
