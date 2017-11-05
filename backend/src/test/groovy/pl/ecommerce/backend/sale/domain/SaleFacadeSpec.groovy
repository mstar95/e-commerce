package pl.ecommerce.backend.sale.domain

import pl.ecommerce.backend.payment.domain.PaymentFacade
import pl.ecommerce.backend.product.domain.ProductFacade
import pl.ecommerce.backend.time.domain.TimeManager
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification

abstract class SaleFacadeSpec extends Specification {

    def saleRepositoryStub = new SaleInMemoryRepository()
    def archivedSaleRepository = new ArchivedSaleInMemoryRepository()
    def productFacadeStub = Stub(ProductFacade)
    def userFacadeStub = Stub(UserFacade)
    def paymentFacadeStub = Stub(PaymentFacade)
    def timeManagerStub = Stub(TimeManager)

    def saleService = new SaleService(saleRepositoryStub, productFacadeStub, userFacadeStub)
    def salePaymentsService = new SalePaymentsService(saleRepositoryStub, archivedSaleRepository,
            paymentFacadeStub ,productFacadeStub, userFacadeStub, timeManagerStub)
    def saleFacade = new SaleFacade(saleService, salePaymentsService)
}
