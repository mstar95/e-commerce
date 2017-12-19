package pl.ecommerce.backend.payment.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.ecommerce.backend.user.domain.UserFacade;

@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
class PaymentConfiguration {

    @Bean
    PaymentFacade paymentFacade(WalletRepository walletRepository, @Lazy UserFacade userFacade){

        TransactionsPaymentService transactionsPaymentService = new TransactionsPaymentService(walletRepository);
        BasicOperationPaymentService basicOperationPaymentService
                = new BasicOperationPaymentService(walletRepository, userFacade);
        return new PaymentFacade(transactionsPaymentService, basicOperationPaymentService);
    }

}
