package pl.ecommerce.backend.base

import pl.ecommerce.backend.sale.dto.BidAuctionDto
import pl.ecommerce.backend.sale.dto.CreateSaleDto


class SaleTestData {

    static CreateSaleDto createSaleDto1 = CreateSaleDto.builder()
            .name("Yeezy")
            .description("The best of boots")
            .deadline(TimeTestData.BASIC_DATA_LATER)
            .imageId(0L)
            .price(new BigDecimal(100))
            .buyNow(true)
            .build()

    static CreateSaleDto createSaleDtoAuction = CreateSaleDto.builder()
            .name("Yeezy")
            .description("The best of boots")
            .deadline(TimeTestData.BASIC_DATA_LATER)
            .imageId(0L)
            .price(new BigDecimal(100))
            .buyNow(false)
            .build()
}
