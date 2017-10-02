package pl.ecommerce.backend.sale.domain;

import org.springframework.context.annotation.Bean;
import pl.ecommerce.backend.product.domain.ProductFacade;

class SaleConfiguration {

    @Bean
    SaleFacade saleFacade(SaleRepository saleRepository,
                          ProductFacade productFacade){
        SaleService saleService = new SaleService(saleRepository, productFacade);
        return new SaleFacade(saleService);
    }
}
