package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.dtos.AmountAfterTransferDto;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.payment.dtos.ReducePointsDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PaymentFacade {
    private final PaymentService paymentService;

    public Long createWallet(long userId) {
        return paymentService.createWallet(userId);
    }

    public BigDecimal getAmountByUserID(long userId) {
        return paymentService.getAmountByUserID(userId);
    }

    public BigDecimal chargePoints(ChargePointsDto chargePointsDto){
        return paymentService.chargePoints(chargePointsDto);
    }

    public BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        return paymentService.reducePoints(reducePointsDto);
    }

    public AmountAfterTransferDto transferPoints(TransferPointsDto transferPointsDto) {
        return paymentService.transferPoints(transferPointsDto);
    }
}
