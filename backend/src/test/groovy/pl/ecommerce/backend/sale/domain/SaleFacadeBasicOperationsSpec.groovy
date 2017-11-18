package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.product.dto.ProductDto
import pl.ecommerce.backend.sale.exceptions.SaleCreationException
import pl.ecommerce.backend.sale.exceptions.SaleInDtoArgumentsException

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
        sale.userId == ProductTestData.createdProductDto0.userId
        sale.name == productDto.name
        sale.multi == saleInDto.multi
        sale.amount == 1
        sale.price == saleInDto.price
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
        sale.multi == saleDto.multi
        sale.amount == 1
        sale.price == saleDto.price
    }

    def "Should create an multi sale"() {
        given:
        def saleDto = SaleTestData.existingProductMultiSaleInDto0
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
        sale.multi == saleDto.multi
        sale.amount == saleDto.amount
        sale.price == saleDto.price
    }

    def "Should throw an create sale exception because user not exist"() {
        given:
        def saleDto = SaleTestData.existingProductSaleInDto1
        when:
        saleFacade.createSale(saleDto)
        then:
        thrown(SaleCreationException.class)
    }

    def "Should throw an create sale exception because product not exist"() {
        given:
        def dto = SaleTestData.newProductSaleInDto0
        when:
        saleFacade.createSale(dto)
        then:
        thrown(SaleCreationException.class)
    }

    def "Should throw an create sale exception because is bad corelation beetwen amount and multi values"() {
        when:
        saleFacade.createSale(dto)
        then :
        thrown(expectedException)
        where:
        dto || expectedException
        SaleTestData.existingProductMultiSaleInDtoWrong0 || SaleInDtoArgumentsException.class
        SaleTestData.existingProductMultiSaleInDtoWrong1 || SaleInDtoArgumentsException.class
        SaleTestData.existingProductMultiSaleInDtoWrong2 || SaleInDtoArgumentsException.class
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
