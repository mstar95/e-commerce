package pl.ecommerce.backend.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ecommerce.backend.payment.domain.PaymentFacade;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          PaymentFacade paymentFacade,
                          PasswordEncoder passwordEncoder) {
        UserService userService = new UserService(userRepository, paymentFacade, passwordEncoder);
        return new UserFacade(userService);
    }
}
