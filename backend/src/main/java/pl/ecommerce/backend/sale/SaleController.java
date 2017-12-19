package pl.ecommerce.backend.sale;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.query.QuerySaleDetail;
import pl.ecommerce.backend.sale.query.QuerySaleDetailRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleController {

    private final QuerySaleDetailRepository querySaleRepository;
    private final SaleFacade saleFacade;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SaleOutDto>> all() {
        querySaleRepository.findAll();
        return ResponseEntity.ok(querySaleRepository.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Long> create(@RequestBody CreateSaleDto createSaleDto) {
        return ResponseEntity.ok(saleFacade.createSale(createSaleDto));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<SaleDetailDto> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(saleFacade.findById(id));
    }
}
