package pl.ecommerce.backend.product.domain

import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification

class ProductFacadeSpec extends Specification{

    def productInMemoryRepository = new ProductInMemoryRepository()
    def userFacadeStub = Stub(UserFacade)
    def productServiceStub = new ProductService(productInMemoryRepository, userFacadeStub)
    def productFacade = new ProductFacade(productServiceStub)

    def setup(){
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
    }

    def "Should create products"(){
        given:
        def dto = ProductTestData.productDto0
        when:
        def productId = productFacade.createProduct(dto)
        def productOpt = productFacade.find(productId)
        then:
        productOpt.isPresent()
        def product = productOpt.get()
        product.id == productId
        product.userId == UserTestData.USER_ID_1
        product.name == dto.name
    }

    def "Should update products"(){
        given:
        def dto = ProductTestData.productDto0
        def productName = ProductTestData.NEW_PRODUCT_NAME
        when:
        def productId = productFacade.createProduct(dto)
        dto.setId(productId)
        dto.setName(productName)
        def updatedProductId = productFacade.createProduct(dto)
        def productOpt = productFacade.find(productId)
        then:
        productOpt.isPresent()
        def product = productOpt.get()
        product.id == productId
        product.id == updatedProductId
        product.userId == UserTestData.USER_ID_1
        product.name == productName
    }

    def "Should find a empty optional"(){
        when:
        def productOpt = productFacade.find(ProductTestData.NOT_EXISTING_PRODUCT_ID)
        then:
        !productOpt.isPresent()
    }

}
