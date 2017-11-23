package pl.ecommerce.backend.payment.domain

import pl.ecommerce.backend.base.PaymentTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.payment.exceptions.CreateWalletException
import pl.ecommerce.backend.payment.exceptions.ReducePointsException
import pl.ecommerce.backend.user.domain.UserFacade
import spock.lang.Specification


class BasicPaymentFacadeSpec  extends Specification {
    def userFacadeStub = Stub(UserFacade)
    def walletRepository = new WalletInMemoryRepository()
    def paymentService = new PaymentService(walletRepository)
    def paymentFacade = new PaymentFacade(paymentService)

    def setup() {
        userFacadeStub.getCurrentUserId() >> UserTestData.USER_ID_1
    }

    def "should succesfully create a wallet"() {
        when:
        def walletId = paymentFacade.createWallet(UserTestData.USER_ID_1)
        def amount = paymentFacade.getAmountByUserID(UserTestData.USER_ID_1)
        then:
        walletId != null
        amount == BigDecimal.ZERO
    }

    def "should succesfully charge a wallet"() {
        given:
        def dto = PaymentTestData.chargePointsDto0
        paymentFacade.createWallet(UserTestData.USER_ID_1)
        when:
        def amount = paymentFacade.chargePoints(dto)
        then:
        amount == dto.getAmount()
    }

    def "should throw reducePointseException"() {
        given:
        def dto = PaymentTestData.reducePointsDto0
        paymentFacade.createWallet(UserTestData.USER_ID_1)
        when:
        paymentFacade.reducePoints(dto)
        then:
        thrown(ReducePointsException.class)
    }

    def "should throw a wallet creation exception"() {
        when:
        paymentFacade.createWallet(UserTestData.USER_ID_2)
        then:
        thrown(CreateWalletException.class)
    }

    def "should throw a wallet creation exception 2"() {
        given:
        paymentFacade.createWallet(UserTestData.USER_ID_1)
        when:
        paymentFacade.createWallet(UserTestData.USER_ID_1)
        then:
        thrown(CreateWalletException.class)
    }

}
