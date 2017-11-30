package pl.ecommerce.backend.user.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

class UserInMemoryRepository  extends InMemoryRepository<User> implements UserRepository {

    @Override
    public User save(User entity) {
        Long id =  entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

}