package pl.ecommerce.backend.payment.domain;

import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.payment.dtos.ReducePointsDto;
import pl.ecommerce.backend.payment.exceptions.FindWalletException;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.math.BigDecimal;

class BasicOperationPaymentService extends AbstractPaymentService {

    private final UserFacade userFacade;

    public BasicOperationPaymentService(WalletRepository walletRepository, UserFacade userFacade) {
        super(walletRepository);
        this.userFacade = userFacade;
    }

    BigDecimal chargePoints(ChargePointsDto chargePointsDto) {
        Long currentUserId = userFacade.getCurrentUserId();
        Wallet wallet = walletRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new FindWalletException(currentUserId));
        BigDecimal points = addPoints(chargePointsDto.getAmount(), wallet::getPoints, wallet::setPoints);
        wallet.setPoints(points);
        walletRepository.save(wallet);
        return points;
    }

    BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        Wallet wallet = walletRepository.findByUserId(reducePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(reducePointsDto.getUserId()));
        return substractPoints(reducePointsDto.getAmount(), wallet::getPoints,
                wallet::setPoints, wallet.getId());
    }
}
