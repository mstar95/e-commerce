package pl.ecommerce.backend.sale.domain;

import org.springframework.context.annotation.Bean;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.user.domain.UserFacade;

class SaleConfiguration {

    @Bean
    SaleFacade saleFacade(SaleRepository saleRepository,
                          ProductFacade productFacade,
                          UserFacade userFacade){
        SaleService saleService = new SaleService(saleRepository, productFacade, userFacade);
        return new SaleFacade(saleService);
    }
}
