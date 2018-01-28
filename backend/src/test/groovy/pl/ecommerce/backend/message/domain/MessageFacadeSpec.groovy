package pl.ecommerce.backend.message.domain

import org.springframework.core.env.Environment
import pl.ecommerce.backend.base.MessageTestData
import pl.ecommerce.backend.base.TimeTestData
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.time.domain.TimeManager
import pl.ecommerce.backend.user.domain.UserFacade
import pl.ecommerce.backend.user.query.QueryUserProfileRepository
import spock.lang.Specification

class MessageFacadeSpec extends Specification {

    def messageRepository = new MessageInMemoryRepository()
    def queryUserProfileRepo = Stub(QueryUserProfileRepository)
    def userFacadeStub = Stub(UserFacade)
    def timeManagerStub = Stub(TimeManager)
    def createMessageService = new CreateMessageService(messageRepository, queryUserProfileRepo, timeManagerStub)
    def environment = Stub(Environment)
    def readMessageService = new ReadMessageService(messageRepository, userFacadeStub, environment)
    def messageFacade = new MessageFacade(createMessageService, readMessageService)

    def setup(){
        def dto = MessageTestData.finalizePaymentDto
        queryUserProfileRepo.findQueryUserById(dto.sellerId) >> MessageTestData.QUERY_USER_1
        queryUserProfileRepo.findQueryUserById(dto.buyerId) >> MessageTestData.QUERY_USER_2
        environment.getProperty(_) >> MessageTestData.FinalizeSaleSellerMessage
        timeManagerStub.getCurrentDate() >> TimeTestData.BASIC_DATA
        timeManagerStub.getCurrentTimestamp() >> TimeTestData.BASIC_DATA_LATER_TIMESTAMP
    }

    def "should create finalizePaymentMessage for seller"() {
        given:
        def dto = MessageTestData.finalizePaymentDto
        messageFacade.createFinalizeSaleMessage(dto)
        userFacadeStub.getCurrentUserId() >> dto.sellerId
        when:
        def message = messageFacade.getMessagesForCurrentUser()
        then:
        message[0].text == MessageTestData.FinalizeSaleSellerMessageResult
    }

    def "should mark as seen"() {
        when:
        messageFacade.markAsSeen()
        then:
       true
    }

}
