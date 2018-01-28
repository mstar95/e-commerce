package pl.ecommerce.backend.image.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ImageInMemoryRepository  extends InMemoryRepository<Image> implements ImageRepository {

    @Override
    public Image save(Image entity) {
        Long id = entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return repository.values().stream()
                .filter(message -> message.getId().equals(id))
                .findAny();
    }
}