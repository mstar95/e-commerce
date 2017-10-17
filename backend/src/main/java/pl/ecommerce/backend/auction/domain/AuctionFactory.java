package pl.ecommerce.backend.auction.domain;

import pl.ecommerce.backend.auction.dto.AuctionInDto;
import pl.ecommerce.backend.auction.dto.AuctionOutDto;
import pl.ecommerce.backend.product.dto.ProductDto;

class AuctionFactory {

    static Auction createAuction(AuctionInDto auctionInDto) {
        return Auction.builder()
                .id(auctionInDto.getId())
                .productId(auctionInDto.getProductId())
                .build();
    }

    static AuctionOutDto createAuctionOutDto(Auction auction, ProductDto productDto) {
        return AuctionOutDto.builder()
                .id(auction.getId())
                .userId(auction.getUserId())
                .productId(productDto.getId())
                .name(productDto.getName())
                .build();
    }

    static Auction createAuction(AuctionInDto auctionInDto, ProductDto productDto) {
        return Auction.builder()
                .id(auctionInDto.getId())
                .userId(productDto.getUserId())
                .productId(auctionInDto.getProductId())
                .build();
    }
}
