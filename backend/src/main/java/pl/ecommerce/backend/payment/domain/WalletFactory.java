package pl.ecommerce.backend.payment.domain;

import pl.ecommerce.backend.payment.dtos.AmountAfterTransferDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;

import java.math.BigDecimal;

class WalletFactory {

    static Wallet createWallet(Long userId) {
        return Wallet.builder().userId(userId).points(BigDecimal.ZERO).build();
    }

    static AmountAfterTransferDto createAmountAfterTransferDto(TransferPointsDto transferPointsDto,
                                                               BigDecimal pointsFrom,
                                                               BigDecimal pointsTo) {
        return AmountAfterTransferDto.builder()
                .fromId(transferPointsDto.getFromId())
                .toId(transferPointsDto.getToId())
                .fromAmount(pointsFrom)
                .toAmount(pointsTo).build();
    }
}
