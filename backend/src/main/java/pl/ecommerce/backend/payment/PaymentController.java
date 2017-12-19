package pl.ecommerce.backend.payment;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.BidAuctionDto;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
class PaymentController {

    private final PaymentFacade paymentFacade;
    private final SaleFacade saleFacade;

    @PostMapping("/charge-points")
    public ResponseEntity<BigDecimal> findNameByName(@RequestBody ChargePointsDto chargePointsDto) {
        return ResponseEntity.ok(paymentFacade.chargePoints(chargePointsDto));
    }

    @GetMapping(value = "/buy/{id}")
    public ResponseEntity<ArchivedSaleDto> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(saleFacade.finalizeSale(id).orElse(ArchivedSaleDto.builder().build()));
    }

    @PostMapping(value = "/bid")
    public ResponseEntity<Boolean> bid(@RequestBody BidAuctionDto bidAuctionDto) {
        return ResponseEntity.ok(saleFacade.bidAuction(bidAuctionDto));
    }

}
