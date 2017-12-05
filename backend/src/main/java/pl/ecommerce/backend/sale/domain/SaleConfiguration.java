package pl.ecommerce.backend.sale.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
class SaleConfiguration {

    @Bean
    SaleFacade saleFacade(SaleRepository saleRepository,
                          ArchivedSaleRepository archivedSaleRepository,
                          UserFacade userFacade,
                          PaymentFacade paymentFacade,
                          TimeManager timeManager){
        SaleService saleService = new SaleService(saleRepository, userFacade, timeManager);
        SalePaymentsService salePaymentsService = new SalePaymentsService(saleRepository, archivedSaleRepository,
                paymentFacade, userFacade, timeManager);
        AuctionService auctionService = new AuctionService(salePaymentsService, saleRepository, paymentFacade, userFacade);
        return new SaleFacade(saleService, salePaymentsService, auctionService);
    }
}
