package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

class ArchivedSaleInMemoryRepository  extends InMemoryRepository<ArchivedSale> implements ArchivedSaleRepository {

    @Override
    public ArchivedSale save(ArchivedSale entity) {
        Long id =  entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

}