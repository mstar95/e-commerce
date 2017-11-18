package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.TimeTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.sale.dto.FinalizeSaleDto

class SaleFacadePaymentsSpec extends SaleFacadeSpec {

    def setup() {
        productFacadeStub.find(ProductTestData.createdProductDto0.id) >> Optional.of(ProductTestData.createdProductDto0)
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
        timeManagerStub.getCurrentDate() >> TimeTestData.BASIC_DATA
    }

    def "Should finalize single sale"() {
        given:
        def saleDto = SaleTestData.existingProductSaleInDto0
        def saleId = saleFacade.createSale(saleDto)
        def finalizeSaleDto = new FinalizeSaleDto(saleId,1)
        when:
        def saleOpt = saleFacade.finalizeSale(finalizeSaleDto)
        then:
        !saleFacade.find(saleId).isPresent()
        saleOpt.isPresent()
        def archivedSale = saleOpt.get()
        archivedSale.name == ProductTestData.productDto0.name
        archivedSale.price == saleDto.price
        archivedSale.productId == saleDto.productId
        archivedSale.transactionDate == TimeTestData.BASIC_DATA
        archivedSale.ownerId== UserTestData.USER_ID_1
        archivedSale.clientId == ProductTestData.createdProductDto0.userId
        archivedSale.oldId == saleId
        archivedSale.multi == saleDto.multi
        archivedSale.amount == finalizeSaleDto.amount
    }

    def "Should finalize multiple sale"() {
        given:
        def saleDto = SaleTestData.existingProductMultiSaleInDto0
        def saleId = saleFacade.createSale(saleDto)
        def finalizeSaleDto = new FinalizeSaleDto(saleId,4)
        when:
        def archivedSaleOpt = saleFacade.finalizeSale(finalizeSaleDto)
        then:
        def saleOpt = saleFacade.find(saleId)
        saleOpt.isPresent()
        archivedSaleOpt.isPresent()
        def archivedSale = archivedSaleOpt.get()
        archivedSale.name == ProductTestData.productDto0.name
        archivedSale.price == saleDto.price
        archivedSale.productId == saleDto.productId
        archivedSale.transactionDate == TimeTestData.BASIC_DATA
        archivedSale.ownerId== UserTestData.USER_ID_1
        archivedSale.clientId == ProductTestData.createdProductDto0.userId
        archivedSale.oldId == saleId
        archivedSale.multi == saleDto.multi
        archivedSale.amount == finalizeSaleDto.amount
        def sale =  saleOpt.get()
        sale.amount == saleDto.amount - finalizeSaleDto.amount
    }
}
