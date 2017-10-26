package pl.ecommerce.backend.user.domain;

import org.springframework.context.annotation.Bean;

class UserConfiguration {
    @Bean
    UserFacade userFacade() {
        return new UserFacade();
    }
}
