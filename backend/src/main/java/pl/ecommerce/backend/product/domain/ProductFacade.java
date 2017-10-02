package pl.ecommerce.backend.product.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.product.dto.ProductDto;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public Long createProduct(ProductDto productDto) {
        Product product = ProductFactory.createProduct(productDto);
        return productService.createProduct(product);
    }

    public Optional<ProductDto> find(Long productId) {
        return productService.find(productId).map(ProductFactory::createProductDto);
    }
}
