package pl.ecommerce.backend.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.BidAuctionDto;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
class PaymentController {

    private final PaymentFacade paymentFacade;
    private final SaleFacade saleFacade;

    @PostMapping("/charge")
    public ResponseEntity<BigDecimal> findNameByName(@Valid @RequestBody ChargePointsDto chargePointsDto) {
        return ResponseEntity.ok(paymentFacade.chargePoints(chargePointsDto));
    }

    @PostMapping(value = "/{id}/buy")
    public ResponseEntity<Long> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(saleFacade.finalizeSale(id).orElse(0L));
    }

    @PostMapping(value = "/{id}/bid")
    public ResponseEntity<Boolean> bid(@RequestBody BidAuctionDto bidAuctionDto) {
        return ResponseEntity.ok(saleFacade.bidAuction(bidAuctionDto));
    }

}
