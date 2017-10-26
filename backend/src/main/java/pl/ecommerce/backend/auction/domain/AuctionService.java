package pl.ecommerce.backend.auction.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.auction.dto.AuctionOutDto;
import pl.ecommerce.backend.auction.exceptions.AuctionCreationException;
import pl.ecommerce.backend.auction.exceptions.AuctionFindException;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class AuctionService {

    private final AuctionRepository auctionRepository;
    private final ProductFacade productFacade;
    private final UserFacade userFacade;

    Long createAuction(Auction auction){
        ProductDto productDto = getProductById(auction.getProductId());
        validateAndSetUserId(auction, productDto);
        return auctionRepository.save(auction).getId();
    }

    private void validateAndSetUserId(Auction auction, ProductDto productDto) {
        Long currentUserId = userFacade.getCurrentUserId();
        if (!productDto.getUserId().equals(currentUserId)) {
            throw new AuctionCreationException("Current user is not owner of product with id:" + productDto.getId());
        }
        auction.setUserId(productDto.getUserId());
    }

    Long createAuctionOfNewProduct(Auction auction, ProductDto productDto) {
        Long productId = productFacade.createProduct(productDto);
        auction.setProductId(productId);
        return auctionRepository.save(auction).getId();
    }

    Optional<AuctionOutDto> find(Long auctionId) {
        Optional<Auction> auctionOpt = auctionRepository.findById(auctionId);
        return auctionOpt.map(this::getProductByIdAndMergeWithAuction);
    }

    List<AuctionOutDto> findByUserId(Optional<Long> userId) {
        List<Auction> auctions = auctionRepository.findAuctionsByUserId(userId
                        .orElseThrow(() -> new AuctionFindException("There is no userId")));
        return auctions.stream()
                .map(this::getProductByIdAndMergeWithAuction)
                .collect(Collectors.toList());
    }

    private AuctionOutDto getProductByIdAndMergeWithAuction(Auction auction){
        ProductDto productDto = getProductById(auction.getProductId());
        return AuctionFactory.createAuctionOutDto(auction, productDto);
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new AuctionCreationException("Product with id " + productId + " not exist"));
    }
}
