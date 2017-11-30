package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.TimeTestData
import pl.ecommerce.backend.base.UserTestData

class SaleFacadeBasicOperationsSpec extends SaleFacadeSpec {

    def setup() {
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
    }

    def "Should create an sale"() {
        given:
        def saleInDto = SaleTestData.createSaleDto1
        when:
        def saleId = saleFacade.createSale(saleInDto)
        def saleOpt = saleRepositoryStub.findById(saleId)
        then:
        saleOpt.isPresent()
        def sale = saleOpt.get()
        sale.id == saleId
        sale.userId == UserTestData.USER_ID_1
        sale.name == saleInDto.name
        sale.description == saleInDto.description
        sale.image == sale.image
        sale.price == saleInDto.price
        sale.isBuyNow()
        sale.created == TimeTestData.BASIC_DATA_TIMESTAMP
        sale.deadline == TimeTestData.BASIC_DATA_LATER_TIMESTAMP
    }
}
