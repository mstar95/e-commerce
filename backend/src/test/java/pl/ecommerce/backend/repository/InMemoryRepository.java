package pl.ecommerce.backend.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryRepository<T> {

    protected final Map<Long, T> repository;
    protected Long nextId;

    protected InMemoryRepository() {
        this.repository = new HashMap<>();
        this.nextId = 1L;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }
}
