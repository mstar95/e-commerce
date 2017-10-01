package pl.ecommerce.backend.product.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.product.dto.ProductDto;

@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public Long createProduct(ProductDto productDto) {
        Product product = ProductFactory.productDtoToProduct(productDto);
        return productService.createProduct(product);
    }

}
