package pl.ecommerce.backend.message.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.message.dto.CreateFinalizeSaleMessageDto;
import pl.ecommerce.backend.message.dto.MessageOutDto;

import java.util.List;

@RequiredArgsConstructor
public class MessageFacade {

    private final CreateMessageService createMessageService;
    private final ReadMessageService readMessageService;

    public void createFinalizeSaleMessage(CreateFinalizeSaleMessageDto dto) {
        createMessageService.createFinalizeSaleMessage(dto);
    }

    public List<MessageOutDto> getMessagesForCurrentUser() {
        return readMessageService.getMessagesForCurrentUser();
    }

    public void markAsSeen() {
        readMessageService.markAsSeen();
    }

}
