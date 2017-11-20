package pl.ecommerce.backend.base

import pl.ecommerce.backend.sale.dto.CreateSaleDto


class SaleTestData {

    static CreateSaleDto createSaleDto1 = CreateSaleDto.builder()
            .name("Yeezy")
            .image(new byte[1])
            .price(new BigDecimal(100)).build()
}
