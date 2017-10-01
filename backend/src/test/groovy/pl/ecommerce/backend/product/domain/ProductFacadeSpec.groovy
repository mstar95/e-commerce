package pl.ecommerce.backend.product.domain

import pl.ecommerce.backend.product.dto.ProductDto
import spock.lang.Specification

class ProductFacadeSpec extends Specification{

    private final String PRODUCT_NAME = "Yeezy"

    def productRepositoryStub = Stub(ProductRepository)
    def productServiceStub = new ProductService(productRepositoryStub)
    def productFacade = new ProductFacade(productServiceStub)

    def createProductDto(String name){
        return ProductDto.builder()
            .userId(1)
            .name(name)
            .build()
    }

    def createProduct(String name){
        return Product.builder()
                .id(1)
                .userId(1)
                .name(name)
                .build()
    }

    def setup() {
        productRepositoryStub.save(_) >> createProduct(PRODUCT_NAME)
    }

    def "Should create an product"(){
        given:
        def dto = createProductDto(PRODUCT_NAME)
        when:
        def productId = productFacade.createProduct(dto)
        then:
        productId == 1
    }

}
