package pl.ecommerce.backend.product.domain

import pl.ecommerce.backend.product.dto.ProductDto
import spock.lang.Specification

class ProductFacadeSpec extends Specification{

    private final String PRODUCT_NAME = "Yeezy"
    private final Long EXISTING_PRODUCT_ID = 1L
    private final Long NOT_EXISTING_PRODUCT_ID = 2L
    private final Long USER_ID = 1L

    def productRepositoryStub = Stub(ProductRepository)
    def productServiceStub = new ProductService(productRepositoryStub)
    def productFacade = new ProductFacade(productServiceStub)

    def setup() {
        productRepositoryStub.save(_) >> createProduct()
        productRepositoryStub.findById(EXISTING_PRODUCT_ID) >>
                Optional.of(createProduct())
    }

    def "Should create an product"(){
        given:
        def dto = createProductDto()
        when:
        def productId = productFacade.createProduct(dto)
        then:
        productId == EXISTING_PRODUCT_ID
    }

    def "Should find an product"(){
        when:
        def productOpt = productFacade.find(EXISTING_PRODUCT_ID)
        then:
        productOpt.isPresent()
        def product = productOpt.get()
        product.id == EXISTING_PRODUCT_ID
        product.userId == USER_ID
        product.name == PRODUCT_NAME
    }

    def "Should find a empty optional"(){
        when:
        def productOpt = productFacade.find(NOT_EXISTING_PRODUCT_ID)
        then:
        !productOpt.isPresent()
    }

    def createProductDto(){
        return ProductDto.builder()
                .userId(USER_ID)
                .name(PRODUCT_NAME)
                .build()
    }

    def createProduct(){
        return Product.builder()
                .id(EXISTING_PRODUCT_ID)
                .userId(USER_ID)
                .name(PRODUCT_NAME)
                .build()
    }
}
