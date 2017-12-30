package pl.ecommerce.backend.sale.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.message.domain.MessageFacade;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
class SaleConfiguration {

    @Bean
    SaleFacade saleFacade(SaleRepository saleRepository,
                          ElasticSearchSaleRepository elasticSearchSaleRepository,
                          UserFacade userFacade,
                          PaymentFacade paymentFacade,
                          TimeManager timeManager,
                          MessageFacade messageFacade){
        SaleService saleService = new SaleService(saleRepository, elasticSearchSaleRepository, userFacade, timeManager);
        SalePaymentsService salePaymentsService = new SalePaymentsService(saleRepository,elasticSearchSaleRepository,
                paymentFacade, userFacade, messageFacade);
        AuctionService auctionService = new AuctionService(saleRepository, paymentFacade, userFacade, timeManager);
        return new SaleFacade(saleService, salePaymentsService, auctionService);
    }
}
