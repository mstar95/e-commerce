package pl.ecommerce.backend.base

import pl.ecommerce.backend.sale.dto.SaleInDto


class SaleTestData {
    static def NOT_EXISTING_SALE_ID = -1L
    static SaleInDto newProductSaleInDto0 = SaleInDto.builder().build()
    static SaleInDto existingProductSaleInDto0 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id).build()
}
