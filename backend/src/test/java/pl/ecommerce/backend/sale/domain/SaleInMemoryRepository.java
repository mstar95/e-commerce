package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.sql.Timestamp;
import java.util.List;

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
    public List<Sale> findSalesByDeadlineAfterAndBuyNowIsFalse(Timestamp deadline) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }

}
