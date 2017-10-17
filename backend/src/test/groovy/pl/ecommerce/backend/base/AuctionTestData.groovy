package pl.ecommerce.backend.base

import pl.ecommerce.backend.auction.dto.AuctionInDto


class AuctionTestData {
    static def NOT_EXISTING_AUCTION_ID = -1L
    static AuctionInDto newProductAuctionInDto0 = AuctionInDto.builder().build()
    static AuctionInDto existingProductAuctionInDto0 = AuctionInDto.builder()
            .productId(ProductTestData.createdProductDto0.id).build()
}
