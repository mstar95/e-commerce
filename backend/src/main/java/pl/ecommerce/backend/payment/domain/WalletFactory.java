package pl.ecommerce.backend.payment.domain;

import java.math.BigDecimal;

class WalletFactory {
    static Wallet createWallet(Long userId) {
        return Wallet.builder().userId(userId).points(BigDecimal.ZERO).build();
    }
}
