package pl.ecommerce.backend.message.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import pl.ecommerce.backend.message.dto.MessageOutDto;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;

@RequiredArgsConstructor
class ReadMessageService {

    private final MessageRepository messageRepository;
    private final UserFacade userFacade;
    private final Environment environment;

    List<MessageOutDto> getMessagesForCurrentUser() {
        return messageRepository.getMessagesByAddresseeId(userFacade.getCurrentUserId()).stream()
                .map(this::generateMessage)
                .collect(Collectors.toList());
    }

    void markAsSeen(){
        List<Message> messages = messageRepository.getMessagesByAddresseeIdAndSeen(userFacade.getCurrentUserId(), false).stream()
                .map(message -> {
                    message.setSeen(true);
                    return message;
                }).collect(Collectors.toList());
        messageRepository.saveAll(messages);
    }

    private MessageOutDto generateMessage(Message message) {
        return Match(message.getMessageType()).of(
                Case($(MessageType.FinalizeSaleSeller),
                        generateMessage(message, environment.getProperty("sellerFinalizeSaleMessage"))),
                Case($(MessageType.FinalizeSaleBuyer),
                        generateMessage(message, environment.getProperty("buyerFinalizeSaleMessage"))),
                Case($(), MessageOutDto.builder().build()));
    }

    private MessageOutDto generateMessage(Message message, String pattern) {
        return new MessageOutDto(MessageFormat.format(pattern, message.getPartnerName(), message.getProductName(),
                message.getAmount()), message.isSeen(), message.getCreated().toLocalDateTime());
    }
}
