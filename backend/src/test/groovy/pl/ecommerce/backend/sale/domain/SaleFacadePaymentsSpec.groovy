package pl.ecommerce.backend.sale.domain


import pl.ecommerce.backend.base.SaleTestData
import pl.ecommerce.backend.base.TimeTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.sale.dto.BidAuctionDto


class SaleFacadePaymentsSpec extends SaleFacadeSpec {

    def setup() {
        userFacadeStub.getCurrentUserId() >>> [ UserTestData.USER_ID_1, UserTestData.USER_ID_2]
        timeManagerStub.getCurrentDate() >> TimeTestData.BASIC_DATA
    }

    def "Should finalize sale"() {
        given:
        def saleInDto = SaleTestData.createSaleDto1
        def saleId = saleFacade.createSale(saleInDto)
        when:
        def saleOpt = saleFacade.finalizeSale(saleId)
        then:
        !saleRepositoryStub.findById(saleId).isPresent()
        saleOpt.isPresent()
        def archivedSale = saleOpt.get()
        archivedSale.name == saleInDto.name
        archivedSale.price == saleInDto.price
        archivedSale.image == saleInDto.image
        archivedSale.transactionDate == TimeTestData.BASIC_DATA_TIMESTAMP
        archivedSale.ownerId== UserTestData.USER_ID_1
        archivedSale.clientId == UserTestData.USER_ID_2
    }

    def "Should bid auction"() {
        given:
        def saleInDto = SaleTestData.createSaleDtoAuction
        def saleId = saleFacade.createSale(saleInDto)
        def bidDto = new BidAuctionDto(saleId, new BigDecimal(50))
        when:
        def result = saleFacade.bidAuction(bidDto)
        then:
        result
    }
}
