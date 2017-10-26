package pl.ecommerce.backend.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.user.domain.UserFacade;

@Configuration
class ProductConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository,
                                UserFacade userFacade) {
        ProductService productService = new ProductService(productRepository, userFacade);
        return new ProductFacade(productService);
    }
}
