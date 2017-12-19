package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.dtos.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PaymentFacade {
    private final TransactionsPaymentService transactionsPaymentService;
    private final BasicOperationPaymentService basicOperationPaymentService;

    public Long createWallet(long userId) {
        return transactionsPaymentService.createWallet(userId);
    }

    public BigDecimal getAmountByUserID(long userId) {
        return transactionsPaymentService.getAmountByUserID(userId);
    }

    public BigDecimal chargePoints(ChargePointsDto chargePointsDto){
        return basicOperationPaymentService.chargePoints(chargePointsDto);
    }

    public BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        return basicOperationPaymentService.reducePoints(reducePointsDto);
    }

    public AmountAfterTransferDto transferPoints(TransferPointsDto transferPointsDto) {
        return transactionsPaymentService.transferPoints(transferPointsDto);
    }

    public boolean lockPoints(LockPointsDto lockPointsDto) {
        return transactionsPaymentService.lockPoints(lockPointsDto.getUserId(), lockPointsDto.getAmount());
    }

    public boolean unLockPoints(LockPointsDto lockPointsDto) {
        return transactionsPaymentService.unLockPoints(lockPointsDto.getUserId(), lockPointsDto.getAmount());
    }

    public AmountAfterTransferDto transferPointsFromLock(TransferPointsDto transferPointsDto) {
        return transactionsPaymentService.transferPointsFromLock(transferPointsDto);
    }
}
