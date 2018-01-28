package pl.ecommerce.backend.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.query.QuerySaleDetailRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
class SaleController {

    private final QuerySaleDetailRepository querySaleRepository;
    private final SaleFacade saleFacade;

    @GetMapping
    public ResponseEntity<List<SaleOutDto>> all() {
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC, "created");
        return ResponseEntity.ok(querySaleRepository.findAll(pageable).getContent());
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateSaleDto createSaleDto) {
        return ResponseEntity.ok(saleFacade.createSale(createSaleDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleDetailDto> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(saleFacade.findById(id));
    }
}
