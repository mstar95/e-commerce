package pl.ecommerce.backend.message.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.List;
import java.util.stream.Collectors;

class MessageInMemoryRepository extends InMemoryRepository<Message> implements MessageRepository {

    @Override
    public Message save(Message entity) {
        Long id = entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public List<Message> getMessagesByAddresseeId(Long id) {
        return repository.values().stream()
                .filter(message -> message.getId().equals(id))
                .collect(Collectors.toList());
    }


}
