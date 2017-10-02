package pl.ecommerce.backend.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository){
        ProductService productService = new ProductService(productRepository);
        return new ProductFacade(productService);
    }
}
