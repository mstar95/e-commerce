package pl.ecommerce.backend.payment.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.Optional;

class WalletInMemoryRepository extends InMemoryRepository<Wallet> implements WalletRepository {

    @Override
    public Wallet save(Wallet entity) {
        Long id = entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public Optional<Wallet> findByUserId(Long userId) {
        return repository.values().stream()
                .filter(wallet -> userId.equals(wallet.getId()))
                .findAny();
    }

}
