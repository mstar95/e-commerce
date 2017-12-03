package pl.ecommerce.backend.sale;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class SalePaymentController {
    private final SaleFacade saleFacade;

    @GetMapping(value = "/buy/{id}")
    public ResponseEntity<ArchivedSaleDto> get(@PathVariable("id") long id) {
        return Try.of(() -> saleFacade.finalizeSale(id).get())
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null));
    }
}
