package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.List;
import java.util.stream.Collectors;

class SaleInMemoryRepository extends InMemoryRepository<Sale> implements SaleRepository {

    @Override
    public Sale save(Sale entity) {
        Long id =  entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public List<Sale> findSalesByUserId(Long userId) {
        return repository.values().stream()
                .filter(auction -> userId.equals(auction.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }
}
