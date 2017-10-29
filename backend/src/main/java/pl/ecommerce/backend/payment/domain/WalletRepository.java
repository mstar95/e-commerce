package pl.ecommerce.backend.payment.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface WalletRepository extends Repository<Wallet, Long> {
    Wallet save(Wallet entity);
    Optional<Wallet> findByUserId(Long userId);
}
