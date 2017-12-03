package pl.ecommerce.backend.user.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.Optional;

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
    public Optional<User> getUserByName(String name) {
        return repository.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<User> getUserByNameOrEmail(String name, String email) {
        return repository.values().stream()
                .filter(user -> user.getName().equals(name) || user.getEmail().equals(email))
                .findAny();
    }

}