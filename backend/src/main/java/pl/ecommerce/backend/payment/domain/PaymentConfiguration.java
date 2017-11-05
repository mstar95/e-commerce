package pl.ecommerce.backend.payment.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.user.domain.UserFacade;

@Configuration
class PaymentConfiguration {

    @Bean
    PaymentFacade paymentFacade(WalletRepository walletRepository, UserFacade userFacade){

        PaymentService paymentService = new PaymentService(walletRepository, userFacade);
        return new PaymentFacade(paymentService);
    }

}
