package pl.ecommerce.backend.sale;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.query.QuerySaleDetail;
import pl.ecommerce.backend.sale.query.QuerySaleDetailRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final QuerySaleDetailRepository querySaleRepository;
    private final SaleFacade saleFacade;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SaleOutDto>> all() {
      return Try.of(querySaleRepository::findAll)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Long> create(@RequestBody CreateSaleDto createSaleDto) {
        return ResponseEntity.ok(saleFacade.createSale(createSaleDto));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<QuerySaleDetail> get(@PathVariable("id") long id) {
       return Try.of(() -> querySaleRepository.findById(id))
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }
}
