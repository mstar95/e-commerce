package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.product.dto.ProductDto
import pl.ecommerce.backend.sale.exceptions.SaleCreationException


class SaleFacadeBasicOperationsSpec extends SaleFacadeSpec {

    def setup() {
        productFacadeStub.createProduct(_ as ProductDto) >> ProductTestData.createdProductDto0.id
        productFacadeStub.find(ProductTestData.createdProductDto0.id) >> Optional.of(ProductTestData.createdProductDto0)
        productFacadeStub.find(ProductTestData.createdProductDto1.id) >> Optional.of(ProductTestData.createdProductDto1)
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
    }

    def "Should create an sale and product"() {
        given:
        def saleInDto = SaleTestData.newProductSaleInDto0
        def productDto = ProductTestData.productDto0
        when:
        def saleId = saleFacade.createSaleOfNewProduct(saleInDto, productDto)
        def saleOpt = saleFacade.find(saleId)
        then:
        saleOpt.isPresent()
        def sale = saleOpt.get()
        sale.id == saleId
        sale.userId == productDto.userId
        sale.name == productDto.getName()
    }

    def "Should create an sale"() {
        given:
        def saleDto = SaleTestData.existingProductSaleInDto0
        def productDto = ProductTestData.createdProductDto0
        when:
        def saleId = saleFacade.createSale(saleDto)
        def saleOpt = saleFacade.find(saleId)
        def sales = saleFacade.findByUserId(productDto.userId)
        then:
        saleOpt.isPresent()
        def sale = saleOpt.get()
        sale.id == saleId
        sale.userId == productDto.userId
        sale.name == productDto.getName()
        sales.size() == 1
    }

    def "Should throw an create sale exception"() {
        given:
        def saleDto = SaleTestData.existingProductSaleInDto1
        when:
        saleFacade.createSale(saleDto)
        then:
        thrown(SaleCreationException.class)
    }

    def "Should throw an create sale exception2"() {
        given:
        def dto = SaleTestData.newProductSaleInDto0
        when:
        saleFacade.createSale(dto)
        then:
        thrown(SaleCreationException.class)
    }

    def "Should find a empty optional"() {
        when:
        def saleOpt = saleFacade.find(SaleTestData.NOT_EXISTING_SALE_ID)
        then:
        !saleOpt.isPresent()
    }

    def "Should find 0 sales"() {
        when:
        def sales = saleFacade.findByUserId(UserTestData.USER_ID_1)
        then:
        sales.empty
    }
}
