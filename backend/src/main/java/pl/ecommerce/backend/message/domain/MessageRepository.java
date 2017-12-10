package pl.ecommerce.backend.message.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface MessageRepository extends Repository<Message, Long> {

    Message save(Message entity);

    List<Message> getMessagesByAddresseeId(Long id);

    List<Message> getMessagesByAddresseeIdAndSeen(Long id, boolean seen);

    void saveAll(Iterable<Message> messages);
}
