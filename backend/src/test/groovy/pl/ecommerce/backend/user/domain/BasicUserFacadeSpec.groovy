package pl.ecommerce.backend.user.domain

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pl.ecommerce.backend.base.UserTestData
import pl.ecommerce.backend.payment.domain.PaymentFacade
import spock.lang.Specification

class BasicUserFacadeSpec extends Specification {

    def userRepository = new UserInMemoryRepository()
    def paymentFacadeStub = Stub(PaymentFacade)
    def userService = new UserService(userRepository, paymentFacadeStub, new BCryptPasswordEncoder())
    def userFacade = new UserFacade(userService)

    def "should succesfully create a user"() {
        when:
        def dto = UserTestData.createUserDto
        def userId = userFacade.createUser(dto)
        then:
        userId != null

    }
}