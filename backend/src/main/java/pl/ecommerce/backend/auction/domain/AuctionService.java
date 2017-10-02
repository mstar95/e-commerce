package pl.ecommerce.backend.auction.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.auction.dto.AuctionOutDto;
import pl.ecommerce.backend.auction.exceptions.AuctionCreationException;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;

import java.util.Optional;

@RequiredArgsConstructor
class AuctionService {

    private final AuctionRepository auctionRepository;
    private final ProductFacade productFacade;

    Long createAuction(Auction auction){
        Long productId = auction.getProductId();
        getProductById(productId);
        return auctionRepository.save(auction).getId();
    }

    Optional<AuctionOutDto> find(Long auctionId) {
        Optional<Auction> auctionOpt = auctionRepository.findById(auctionId);
        if(!auctionOpt.isPresent()) {
            return Optional.empty();
        }
        Auction auction = auctionOpt.get();
        ProductDto productDto = getProductById(auction.getProductId());
        return Optional.of(AuctionFactory.createAuctionOutDto(auction, productDto));
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new AuctionCreationException("Product with id " + productId + " not exist"));
    }

    Long createAuctionOfNewProduct(Auction auction, ProductDto productDto) {
        Long productId = productFacade.createProduct(productDto);
        auction.setProductId(productId);
        return auctionRepository.save(auction).getId();
    }
}
