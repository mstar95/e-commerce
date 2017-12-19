package pl.ecommerce.backend.payment.domain;

import pl.ecommerce.backend.payment.dtos.AmountAfterTransferDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.payment.exceptions.FindWalletException;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Function;

class TransactionsPaymentService extends AbstractPaymentService {

    public TransactionsPaymentService(WalletRepository walletRepository) {
        super(walletRepository);
    }

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

}
