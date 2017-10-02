package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.product.domain.ProductFacade
import pl.ecommerce.backend.product.dto.ProductDto
import pl.ecommerce.backend.sale.dto.SaleInDto
import pl.ecommerce.backend.sale.dto.SaleOutDto
import pl.ecommerce.backend.sale.exceptions.SaleCreationException
import spock.lang.Specification

class SaleFacadeSpec extends Specification{
    private final String PRODUCT_NAME = "Yeezy"
    private final Long EXISTING_PRODUCT_ID = 1L
    private final Long NOT_EXISTING_PRODUCT_ID = 100L
    private final Long EXISTING_SALE_ID = 1L
    private final Long NOT_EXISTING_SALE_ID = 100L
    private final Long USER_ID = 1L
    private final Long ANOTHER_USER_ID = 2L

    def saleRepositoryStub = Stub(SaleRepository)
    def productFacadeStub = Stub(ProductFacade)
    def saleServiceStub = new SaleService(saleRepositoryStub, productFacadeStub)
    def saleFacade = new SaleFacade(saleServiceStub)

    def setup() {
        saleRepositoryStub.save(_) >> createSale()
        saleRepositoryStub.findById(EXISTING_SALE_ID) >> Optional.of(createSale())
        saleRepositoryStub.findSalesByUserId(USER_ID) >> [createSale(), createSale()]
        saleRepositoryStub.findSalesByUserId(ANOTHER_USER_ID) >> []
        productFacadeStub.find(EXISTING_PRODUCT_ID) >> Optional.of(createProductDto())
    }

    def "Should create an sale and product"(){
        given:
        def saleDto = createSaleInDto(EXISTING_PRODUCT_ID)
        def productDto = createProductDto()
        when:
        def saleId = saleFacade.createSaleOfNewProduct(saleDto, productDto)
        then:
        saleId == EXISTING_SALE_ID
    }

    def "Should create an sale"(){
        given:
        def dto = createSaleInDto(EXISTING_PRODUCT_ID)
        when:
        def saleId = saleFacade.createSale(dto)
        then:
        saleId == EXISTING_SALE_ID
    }

    def "Should throw an create sale exception"(){
        given:
        def dto = createSaleInDto(NOT_EXISTING_PRODUCT_ID)
        when:
        saleFacade.createSale(dto)
        then:
        thrown(SaleCreationException.class)
    }

    def "Should find a sale"(){
        when:
        def saleOpt = saleFacade.find(EXISTING_SALE_ID)
        then:
        saleOpt.isPresent()
        def  sale = saleOpt.get()
        sale.id == EXISTING_SALE_ID
        sale.userId == USER_ID
        sale.productId == EXISTING_PRODUCT_ID
        sale.name == PRODUCT_NAME
    }

    def "Should find a empty optional"(){
        when:
        def saleOpt = saleFacade.find(NOT_EXISTING_SALE_ID)
        then:
        !saleOpt.isPresent()
    }

    def "Should find 2 sales"(){
        when:
        def sales = saleFacade.findByUserId(USER_ID)
        then:
        sales.size() == 2
    }

    def "Should find 0 sales"(){
        when:
        def sales = saleFacade.findByUserId(ANOTHER_USER_ID)
        then:
        sales.empty
    }

    def createSaleInDto(Long productId){
        return SaleInDto.builder()
                .productId(productId)
                .build()
    }

    def createSaleOutDto(Long productId){
        return SaleOutDto.builder()
                .productId(productId)
                .build()
    }

    def createSale(){
        return Sale.builder()
                .id(EXISTING_SALE_ID)
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
