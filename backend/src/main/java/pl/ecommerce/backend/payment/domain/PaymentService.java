package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.payment.dtos.ReducePointsDto;
import pl.ecommerce.backend.payment.exceptions.CreateWalletException;
import pl.ecommerce.backend.payment.exceptions.FindWalletException;
import pl.ecommerce.backend.payment.exceptions.ReducePointsException;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.math.BigDecimal;

@RequiredArgsConstructor
class PaymentService {
    private final WalletRepository walletRepository;
    private final UserFacade userFacade;

    Long createWallet(Long userId) {
        validateUserId(userId);
        walletRepository.findByUserId(userId)
                .ifPresent((wallet)-> throwCreateWalletException("Cant create second wallet for user with id:" + userId));
        Wallet wallet = WalletFactory.createWallet(userId);
        return walletRepository.save(wallet).getId();
    }


    BigDecimal getAmountByUserID(Long userId) {
        validateUserId(userId);
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new FindWalletException(userId))
                .getPoints();
    }

    BigDecimal chargePoints(ChargePointsDto chargePointsDto) {
        Wallet wallet = walletRepository.findByUserId(chargePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(chargePointsDto.getUserId()));
        BigDecimal points = wallet.getPoints().add(chargePointsDto.getAmount());
        wallet.setPoints(points);
        return points;
    }

    BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        Wallet wallet = walletRepository.findByUserId(reducePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(reducePointsDto.getUserId()));
        BigDecimal points = wallet.getPoints().subtract(reducePointsDto.getAmount());
        if(points.signum() == -1) {
            throw new ReducePointsException(wallet.getPoints(), reducePointsDto);
        }
        wallet.setPoints(points);
        return points;
    }

    private void validateUserId(Long userId) {
        Long currentUserId = userFacade.getCurrentUserId();
        if(userId == null || !userId.equals(currentUserId)) {
            throwCreateWalletException("There is no user with id:" + userId);
        }
    }

    private void throwCreateWalletException(String s){
        throw new CreateWalletException(s);
    }
}
