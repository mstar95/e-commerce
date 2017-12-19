package pl.ecommerce.backend.payment.domain

import pl.ecommerce.backend.base.PaymentTestData
import pl.ecommerce.backend.base.UserTestData
import spock.lang.Specification

class TransferPointsSpec extends Specification {
    def walletRepository = new WalletInMemoryRepository()
    def paymentService = new TransactionsPaymentService(walletRepository)
    def paymentFacade = new PaymentFacade(paymentService)

    def setup() {
        walletRepository.save(new Wallet(1, UserTestData.USER_ID_1, 100, 100))
        walletRepository.save(new Wallet(2, UserTestData.USER_ID_2, 150, 100))
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

    def "should succesfully lock points"() {
        given:
        def dto = PaymentTestData.lockPointsDto0
        when:
        def result = paymentFacade.lockPoints(dto)
        then:
        result
        def amount = paymentFacade.getAmountByUserID(dto.userId)
        amount == 100 - dto.getAmount()
    }

    def "should succesfully unlock points"() {
        given:
        def dto = PaymentTestData.lockPointsDto0
        when:
        def result = paymentFacade.unLockPoints(dto)
        then:
        result
        def amount = paymentFacade.getAmountByUserID(dto.userId)
        amount == 100 - dto.getAmount()
    }

    def "should succesfully execute debt"() {
        given:
        def dto = PaymentTestData.transferPointsDto0
        when:
        def amount = paymentFacade.transferPointsFromLock(dto)
        then:
        amount.fromId == UserTestData.USER_ID_1
        amount.toId == UserTestData.USER_ID_2
        amount.fromAmount == 50
        amount.toAmount == 200
    }
}
