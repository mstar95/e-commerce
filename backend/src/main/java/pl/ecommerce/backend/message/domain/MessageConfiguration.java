package pl.ecommerce.backend.message.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.query.QueryUserProfileRepository;

@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
class MessageConfiguration {

    @Bean
    MessageFacade messageFacade(MessageRepository messageRepository,
                                Environment environment,
                                QueryUserProfileRepository queryUserProfileRepository,
                                UserFacade userFacade,
                                TimeManager timeManager) {
        CreateMessageService createMessageService = new CreateMessageService(messageRepository
                , queryUserProfileRepository, timeManager);
        ReadMessageService readMessageService = new ReadMessageService(messageRepository, userFacade, environment);
        return new MessageFacade(createMessageService, readMessageService);
    }
}
