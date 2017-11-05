package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.dtos.AmountAfterTransferDto;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.payment.dtos.ReducePointsDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
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
        BigDecimal points = addPoints(chargePointsDto.getAmount(), wallet);
        wallet.setPoints(points);
        return points;
    }

    BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        Wallet wallet = walletRepository.findByUserId(reducePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(reducePointsDto.getUserId()));
        BigDecimal points = substractPoints(reducePointsDto.getAmount(), wallet);
        wallet.setPoints(points);
        return points;
    }

    AmountAfterTransferDto transferPoints(TransferPointsDto transferPointsDto) {
        Wallet walletFrom = walletRepository.findByUserId(transferPointsDto.getFromId())
                .orElseThrow(() -> new FindWalletException(transferPointsDto.getFromId()));
        Wallet walletTo = walletRepository.findByUserId(transferPointsDto.getToId())
                .orElseThrow(() -> new FindWalletException(transferPointsDto.getToId()));

        BigDecimal pointsFrom = substractPoints(transferPointsDto.getAmount(), walletFrom);
        BigDecimal pointsTo = addPoints(transferPointsDto.getAmount(), walletTo);
        walletFrom.setPoints(pointsFrom);
        walletTo.setPoints(pointsTo);
        return WalletFactory.createAmountAfterTransferDto(transferPointsDto, pointsFrom, pointsTo);
    }

    private BigDecimal substractPoints(BigDecimal reducePoints, Wallet wallet) {
        BigDecimal points = wallet.getPoints().subtract(reducePoints);
        if(points.signum() == -1) {
            throw new ReducePointsException(wallet.getPoints(), reducePoints, wallet.getUserId());
        }
        return points;
    }

    private BigDecimal addPoints(BigDecimal chargePoints, Wallet wallet) {
        return wallet.getPoints().add(chargePoints);
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
