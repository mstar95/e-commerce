package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.message.domain.MessageFacade
import pl.ecommerce.backend.payment.domain.PaymentFacade
import pl.ecommerce.backend.time.domain.TimeManager
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification

abstract class SaleFacadeSpec extends Specification {

    def saleRepositoryStub = new SaleInMemoryRepository()
    def elasticSaleRepositoryStub = Stub(ElasticSearchSaleRepository)
    def userFacadeStub = Stub(UserFacade)
    def paymentFacadeStub = Stub(PaymentFacade)
    def timeManagerStub = Stub(TimeManager)
    def messageFacade = Stub(MessageFacade)

    def saleService = new SaleService(saleRepositoryStub,elasticSaleRepositoryStub, userFacadeStub, timeManagerStub)
    def salePaymentsService = new SalePaymentsService(saleRepositoryStub,elasticSaleRepositoryStub,
            paymentFacadeStub , userFacadeStub, messageFacade)
    def auctionService = new AuctionService(saleRepositoryStub,paymentFacadeStub,
            userFacadeStub,timeManagerStub )
    def saleFacade = new SaleFacade(saleService, salePaymentsService, auctionService)
}
