package pl.ecommerce.backend.base

import pl.ecommerce.backend.sale.dto.SaleInDto


class SaleTestData {
    static def NOT_EXISTING_SALE_ID = -1L
    static SaleInDto newProductSaleInDto0 = SaleInDto.builder().build()
    static SaleInDto existingProductSaleInDto0 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id)
            .price(new BigDecimal(100))
            .multi(false)
            .build()
    static SaleInDto existingProductSaleInDto1 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto1.id)
            .multi(false)
            .price(new BigDecimal(100)).build()
    static SaleInDto existingProductMultiSaleInDto0 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id)
            .multi(true)
            .amount(10)
            .price(new BigDecimal(100)).build()
    static SaleInDto existingProductMultiSaleInDtoWrong0 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id)
            .multi(false)
            .amount(10)
            .price(new BigDecimal(100)).build()
    static SaleInDto existingProductMultiSaleInDtoWrong1 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id)
            .multi(true)
            .price(new BigDecimal(100)).build()
    static SaleInDto existingProductMultiSaleInDtoWrong2 = SaleInDto.builder()
            .productId(ProductTestData.createdProductDto0.id)
            .multi(true)
            .amount(0)
            .price(new BigDecimal(100)).build()
}
