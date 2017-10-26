package pl.ecommerce.backend.auction.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.auction.dto.AuctionInDto;
import pl.ecommerce.backend.auction.dto.AuctionOutDto;
import pl.ecommerce.backend.product.dto.ProductDto;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AuctionFacade {

    private final AuctionService auctionService;

    public Long createAuction(AuctionInDto auctionInDto){
        Auction auction = AuctionFactory.createAuction(auctionInDto);
        return auctionService.createAuction(auction);
    }

    public Long createAuctionOfNewProduct(AuctionInDto auctionInDto, ProductDto productDto) {
        Auction auction = AuctionFactory.createAuction(auctionInDto, productDto);
        return auctionService.createAuctionOfNewProduct(auction, productDto);
    }

    public Optional<AuctionOutDto> find(Long auctionId) {
        return auctionService.find(auctionId);
    }

    public List<AuctionOutDto> findByUserId(Long userId) {
        return auctionService.findByUserId(Optional.ofNullable(userId));
    }
}
