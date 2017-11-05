package pl.ecommerce.backend.sale.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

@Configuration
class SaleConfiguration {

    @Bean
    SaleFacade saleFacade(SaleRepository saleRepository,
                          ArchivedSaleRepository archivedSaleRepository,
                          ProductFacade productFacade,
                          UserFacade userFacade,
                          PaymentFacade paymentFacade,
                          TimeManager timeManager){
        SaleService saleService = new SaleService(saleRepository, productFacade, userFacade);
        SalePaymentsService salePaymentsService = new SalePaymentsService(saleRepository, archivedSaleRepository,
                paymentFacade,productFacade, userFacade, timeManager);
        return new SaleFacade(saleService, salePaymentsService);
    }
}
