package pl.ecommerce.backend.product.domain;

import pl.ecommerce.backend.product.dto.ProductDto;

class ProductFactory {

    static Product  productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .userId(productDto.getUserId())
                .name(productDto.getName())
                .build();
    }

    static ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .userId(product.getUserId())
                .name(product.getName())
                .build();
    }
}
