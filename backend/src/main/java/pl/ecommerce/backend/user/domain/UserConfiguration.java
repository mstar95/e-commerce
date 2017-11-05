package pl.ecommerce.backend.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade() {
        return new UserFacade();
    }
}
