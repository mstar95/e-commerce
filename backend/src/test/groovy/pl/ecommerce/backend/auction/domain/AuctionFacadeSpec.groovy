package pl.ecommerce.backend.auction.domain

import pl.ecommerce.backend.auction.dto.AuctionInDto
import pl.ecommerce.backend.auction.exceptions.AuctionCreationException
import pl.ecommerce.backend.product.domain.ProductFacade
import pl.ecommerce.backend.product.dto.ProductDto
import spock.lang.Specification

class AuctionFacadeSpec extends Specification {

    private final String PRODUCT_NAME = "Yeezy"
    private final Long EXISTING_PRODUCT_ID = 1L
    private final Long NOT_EXISTING_PRODUCT_ID = 2L
    private final Long EXISTING_AUCTION_ID = 1L
    private final Long NOT_EXISTING_AUCTION_ID = 2L
    private final Long USER_ID = 1L

    def auctionRepositoryStub = Stub(AuctionRepository)
    def productFacadeStub = Stub(ProductFacade)
    def auctionServiceStub = new AuctionService(auctionRepositoryStub, productFacadeStub)
    def auctionFacade = new AuctionFacade(auctionServiceStub)

    def setup() {
        auctionRepositoryStub.save(_) >> createAuction()
        auctionRepositoryStub.findById(EXISTING_AUCTION_ID) >> Optional.of(createAuction())
        productFacadeStub.find(EXISTING_PRODUCT_ID) >> Optional.of(createProductDto())
    }

    def "Should create an auction and product"(){
        given:
        def auctionDto = createAuctionInDto(EXISTING_PRODUCT_ID)
        def productDto = createProductDto()
        when:
        def auctionId = auctionFacade.createAuctionOfNewProduct(auctionDto, productDto)
        then:
        auctionId == EXISTING_AUCTION_ID
    }

    def "Should create an auction"(){
        given:
        def dto = createAuctionInDto(EXISTING_PRODUCT_ID)
        when:
        def auctionId = auctionFacade.createAuction(dto)
        then:
        auctionId == EXISTING_AUCTION_ID
    }

    def "Should throw an create auction exception"(){
        given:
        def dto = createAuctionInDto(NOT_EXISTING_PRODUCT_ID)
        when:
        auctionFacade.createAuction(dto)
        then:
        thrown(AuctionCreationException.class)
    }

    def "Should find a auction"(){
        when:
        def auctionOpt = auctionFacade.find(EXISTING_AUCTION_ID)
        then:
        auctionOpt.isPresent()
        def  auction = auctionOpt.get()
        auction.id == EXISTING_AUCTION_ID
        auction.userId == USER_ID
        auction.productId == EXISTING_PRODUCT_ID
        auction.name == PRODUCT_NAME
    }

    def "Should find a empty optional"(){
        when:
        def auctionOpt = auctionFacade.find(NOT_EXISTING_AUCTION_ID)
        then:
        !auctionOpt.isPresent()
    }

    def createAuctionInDto(Long productId){
        return AuctionInDto.builder()
                .productId(productId)
                .build()
    }

    def createAuctionOutDto(Long productId){
        return AuctionInDto.builder()
                .productId(productId)
                .build()
    }

    def createAuction(){
        return Auction.builder()
                .id(EXISTING_AUCTION_ID)
                .userId(USER_ID)
                .productId(EXISTING_PRODUCT_ID)
                .build()
    }

    def createProductDto(){
        return ProductDto.builder()
                .id(EXISTING_PRODUCT_ID)
                .name(PRODUCT_NAME)
                .build()
    }
}