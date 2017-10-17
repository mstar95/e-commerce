package pl.ecommerce.backend.base

import pl.ecommerce.backend.product.dto.ProductDto

class ProductTestData {
    static def NOT_EXISTING_PRODUCT_ID = -1
    static def NEW_PRODUCT_NAME = "AllStar"
    static ProductDto productDto0 = ProductDto.builder()
            .name("Yeezy")
            .userId(UserTestData.USER_ID_1)
            .build()
    static ProductDto createdProductDto0 =  ProductDto.builder()
            .name(productDto0.name)
            .userId(productDto0.userId)
            .id(1000)
            .build()
}
