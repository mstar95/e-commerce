package pl.ecommerce.backend.message.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import pl.ecommerce.backend.message.dto.CreateFinalizeSaleMessageDto;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.query.QueryUserProfileRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
class CreateMessageService {

    private final MessageRepository messageRepository;
    private final QueryUserProfileRepository userProfileRepository;
    private final TimeManager timeManager;

    void createFinalizeSaleMessage(CreateFinalizeSaleMessageDto dto) {

        String buyerName = userProfileRepository
                .findQueryUserById(dto.getBuyerId()).getName();
        String sellerName = userProfileRepository
                .findQueryUserById(dto.getSellerId()).getName();
        Message messageToSeller = Message.builder().addresseeId(dto.getSellerId())
                .partnerName(sellerName)
                .productName(dto.getProductName())
                .amount(dto.getAmount())
                .seen(false)
                .created(timeManager.getCurrentTimestamp())
                .messageType(MessageType.FinalizeSaleSeller).build();
        Message messageToBuyer = Message.builder().addresseeId(dto.getBuyerId())
                .partnerName(buyerName)
                .productName(dto.getProductName())
                .amount(dto.getAmount())
                .seen(false)
                .created(timeManager.getCurrentTimestamp())
                .messageType(MessageType.FinalizeSaleBuyer).build();
        messageRepository.save(messageToBuyer);
        messageRepository.save(messageToSeller);
    }
}
