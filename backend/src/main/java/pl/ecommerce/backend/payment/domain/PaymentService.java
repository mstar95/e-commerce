package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.dtos.AmountAfterTransferDto;
import pl.ecommerce.backend.payment.dtos.ChargePointsDto;
import pl.ecommerce.backend.payment.dtos.ReducePointsDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.payment.exceptions.CreateWalletException;
import pl.ecommerce.backend.payment.exceptions.FindWalletException;
import pl.ecommerce.backend.payment.exceptions.ReducePointsException;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
class PaymentService {
    private final WalletRepository walletRepository;

    Long createWallet(Long userId) {
        walletRepository.findByUserId(userId)
                .ifPresent((wallet) -> throwCreateWalletException("Cant create second wallet for user with id:" + userId));
        Wallet wallet = WalletFactory.createWallet(userId);
        return walletRepository.save(wallet).getId();
    }

    BigDecimal getAmountByUserID(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new FindWalletException(userId))
                .getPoints();
    }

    BigDecimal chargePoints(ChargePointsDto chargePointsDto) {
        Wallet wallet = walletRepository.findByUserId(chargePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(chargePointsDto.getUserId()));
        BigDecimal points = addPoints(chargePointsDto.getAmount(), wallet::getPoints, wallet::setPoints);
        wallet.setPoints(points);
        return points;
    }

    BigDecimal reducePoints(ReducePointsDto reducePointsDto) {
        Wallet wallet = walletRepository.findByUserId(reducePointsDto.getUserId())
                .orElseThrow(() -> new FindWalletException(reducePointsDto.getUserId()));
        return substractPoints(reducePointsDto.getAmount(), wallet::getPoints,
                wallet::setPoints, wallet.getId());
    }

    AmountAfterTransferDto transferPointsFromLock(TransferPointsDto transferPointsDto) {
        return transferPoints(transferPointsDto, Wallet::getLockedPoints, Wallet::setLockedPoints);
    }

    AmountAfterTransferDto transferPoints(TransferPointsDto transferPointsDto) {
        return transferPoints(transferPointsDto, Wallet::getPoints, Wallet::setPoints);
    }

    private AmountAfterTransferDto transferPoints(TransferPointsDto transferPointsDto,
                                                  Function<Wallet, BigDecimal> biGetter,
                                                  BiConsumer<Wallet, BigDecimal> biSetter) {
        Wallet walletFrom = walletRepository.findByUserId(transferPointsDto.getFromId())
                .orElseThrow(() -> new FindWalletException(transferPointsDto.getFromId()));
        Wallet walletTo = walletRepository.findByUserId(transferPointsDto.getToId())
                .orElseThrow(() -> new FindWalletException(transferPointsDto.getToId()));
        BigDecimal pointsFrom = substractPoints(transferPointsDto.getAmount(),() -> biGetter.apply(walletFrom),
                    value -> biSetter.accept(walletFrom, value) , walletFrom.getId());

        BigDecimal pointsTo = addPoints(transferPointsDto.getAmount(), walletTo::getPoints, walletTo::setPoints);
        return WalletFactory.createAmountAfterTransferDto(transferPointsDto, pointsFrom, pointsTo);
    }

    boolean lockPoints(long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new FindWalletException(userId));
        substractPoints(amount, wallet::getPoints, wallet::setPoints, wallet.getId());
        addPoints(amount, wallet::getLockedPoints, wallet::setLockedPoints);
        return true;
    }

    boolean unLockPoints(long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new FindWalletException(userId));
        substractPoints(amount, wallet::getPoints, wallet::setPoints, wallet.getId());
        addPoints(amount, wallet::getLockedPoints, wallet::setLockedPoints);
        return true;
    }

    private BigDecimal substractPoints(BigDecimal reducePoints, Supplier<BigDecimal> getter,
                                       Consumer<BigDecimal> setter, Long id) {
        BigDecimal points = getter.get().subtract(reducePoints);
        if (points.signum() == -1) {
            throw new ReducePointsException(getter.get(), reducePoints, id);
        }
        setter.accept(points);
        return points;
    }

    private BigDecimal addPoints(BigDecimal chargePoints, Supplier<BigDecimal> getter, Consumer<BigDecimal> setter) {
        BigDecimal result = getter.get().add(chargePoints);
        setter.accept(result);
        return result;
    }

    private void throwCreateWalletException(String s) {
        throw new CreateWalletException(s);
    }

}
