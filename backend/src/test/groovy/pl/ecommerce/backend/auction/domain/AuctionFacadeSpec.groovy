package pl.ecommerce.backend.auction.domain

import pl.ecommerce.backend.auction.exceptions.AuctionCreationException
import pl.ecommerce.backend.base.AuctionTestData
import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.product.domain.ProductFacade
import pl.ecommerce.backend.product.dto.ProductDto
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification

class AuctionFacadeSpec extends Specification {

    def productFacadeStub = Stub(ProductFacade)
    def auctionInMemoryRepository = new AuctionInMemoryRepository()
    def userFacadeStub = Stub(UserFacade)
    def auctionServiceStub = new AuctionService(auctionInMemoryRepository, productFacadeStub, userFacadeStub)
    def auctionFacade = new AuctionFacade(auctionServiceStub)

    def setup() {
        productFacadeStub.createProduct(_ as ProductDto) >> ProductTestData.createdProductDto0.id
        productFacadeStub.find(ProductTestData.createdProductDto0.id) >> Optional.of(ProductTestData.createdProductDto0)
        productFacadeStub.find(ProductTestData.createdProductDto1.id) >> Optional.of(ProductTestData.createdProductDto1)
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
    }

    def "Should create an auction and product"(){
        given:
        def auctionDto = AuctionTestData.newProductAuctionInDto0
        def productDto = ProductTestData.productDto0
        when:
        def auctionId = auctionFacade.createAuctionOfNewProduct(auctionDto, productDto)
        def auctionOpt = auctionFacade.find(auctionId)
        then:
        auctionOpt.isPresent()
        def  auction = auctionOpt.get()
        auction.id == auctionId
        auction.userId == productDto.userId
        auction.name == productDto.getName()
    }

    def "Should create an auction"(){
        given:
        def auctionDto =  AuctionTestData.existingProductAuctionInDto0
        def productDto = ProductTestData.createdProductDto0
        when:
        def auctionId = auctionFacade.createAuction(auctionDto)
        def auctionOpt = auctionFacade.find(auctionId)
        def auctions = auctionFacade.findByUserId(productDto.userId)
        then:
        auctionOpt.isPresent()
        def  auction = auctionOpt.get()
        auction.id == auctionId
        auction.userId == productDto.userId
        auction.name == productDto.getName()
        auctions.size() == 1
    }

    def "Should throw an create auction exception"(){
        given:
        def auctionDto = AuctionTestData.existingProductAuctionInDto1
        when:
        auctionFacade.createAuction(auctionDto)
        then:
        thrown(AuctionCreationException.class)
    }

    def "Should throw an create auction exception2"(){
        given:
        def dto = AuctionTestData.newProductAuctionInDto0
        when:
        auctionFacade.createAuction(dto)
        then:
        thrown(AuctionCreationException.class)
    }

    def "Should find a empty optional"(){
        when:
        def auctionOpt = auctionFacade.find(AuctionTestData.NOT_EXISTING_AUCTION_ID)
        then:
        !auctionOpt.isPresent()
    }

    def "Should find 0 auctions"(){
        when:
        def auctions = auctionFacade.findByUserId(UserTestData.USER_ID_1)
        then:
        auctions.empty
    }
}
