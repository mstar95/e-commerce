package pl.ecommerce.backend.product.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

class ProductInMemoryRepository extends InMemoryRepository<Product> implements ProductRepository {

    @Override
   public Product save(Product entity) {
        Long id =  entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }
}
