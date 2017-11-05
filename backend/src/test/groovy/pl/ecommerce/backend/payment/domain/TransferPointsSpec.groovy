package pl.ecommerce.backend.payment.domain

import pl.ecommerce.backend.base.PaymentTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification

class TransferPointsSpec  extends Specification {
    def userFacadeStub = Stub(UserFacade)
    def walletRepository = Stub(WalletInMemoryRepository)
    def paymentService = new PaymentService(walletRepository, userFacadeStub)
    def paymentFacade = new PaymentFacade(paymentService)

    def setup() {
        walletRepository.findByUserId(UserTestData.USER_ID_1) >>
                wallet(1,UserTestData.USER_ID_1, 100)
        walletRepository.findByUserId(UserTestData.USER_ID_2) >>
                wallet(2,UserTestData.USER_ID_2, 150)
    }

    def "should succesfully transfer points"() {
        given:
        def dto = PaymentTestData.transferPointsDto0
        when:
        def amount = paymentFacade.transferPoints(dto)
        then:
        amount.fromId == UserTestData.USER_ID_1
        amount.toId == UserTestData.USER_ID_2
        amount.fromAmount == 50
        amount.toAmount == 200
    }

    def wallet(id, userId, amount) {
        return Optional.of(new Wallet(id,userId, amount))
    }
}
