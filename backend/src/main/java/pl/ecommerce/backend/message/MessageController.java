package pl.ecommerce.backend.message;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.backend.message.domain.MessageFacade;
import pl.ecommerce.backend.message.dto.MessageOutDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
class MessageController {

    private final MessageFacade messageFacade;

    @GetMapping(value = "")
    public ResponseEntity<List<MessageOutDto>> all() {
        return ResponseEntity.ok(messageFacade.getMessagesForCurrentUser());
    }

    @GetMapping(value = "/seen")
    public ResponseEntity markAsSeen() {
        messageFacade.markAsSeen();
        return ResponseEntity.ok(null);
    }

}
