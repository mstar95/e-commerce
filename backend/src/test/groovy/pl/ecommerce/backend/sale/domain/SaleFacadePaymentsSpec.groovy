package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.base.ProductTestData
import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.TimeTestData
import pl.ecommerce.backend.base.UserTestData

class SaleFacadePaymentsSpec extends SaleFacadeSpec {

    def setup() {
        productFacadeStub.find(ProductTestData.createdProductDto0.id) >> Optional.of(ProductTestData.createdProductDto0)
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
        timeManagerStub.getCurrentDate() >> TimeTestData.BASIC_DATA
    }

    def "Should finalize sale"() {
        given:
        def saleDto = SaleTestData.existingProductSaleInDto0
        def saleId = saleFacade.createSale(saleDto)
        when:
        def saleOpt = saleFacade.finalizeSale(saleId)
        then:
        !saleFacade.find(saleId).isPresent()
        saleOpt.isPresent()
        def archivedSale = saleOpt.get()
        archivedSale.getName() == ProductTestData.productDto0.getName()
        archivedSale.getPrice() == saleDto.getPrice()
        archivedSale.getProductId() == saleDto.getProductId()
        archivedSale.getTransactionDate() == TimeTestData.BASIC_DATA
        archivedSale.getOwnerId() == UserTestData.USER_ID_1
        archivedSale.getClientId() == ProductTestData.createdProductDto0.getUserId()
        archivedSale.getOldId() == saleId
    }
}
