package pl.ecommerce.backend.image.domain

import org.springframework.core.env.Environment
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile
import pl.ecommerce.backend.base.MessageTestData
import pl.ecommerce.backend.message.domain.CreateMessageService
import pl.ecommerce.backend.message.domain.MessageFacade
import pl.ecommerce.backend.message.domain.MessageInMemoryRepository
import pl.ecommerce.backend.message.domain.ReadMessageService
import pl.ecommerce.backend.user.domain.UserFacade
import pl.ecommerce.backend.user.query.QueryUserProfileRepository
import spock.lang.Specification


class ImagaFacadeSpec extends Specification {

    def imageRepository = new ImageInMemoryRepository()
    def imageService = new ImageService(imageRepository)
    def imageFacade = new ImageFacade(imageService)

    def setup() {
    }

    def "should create image "() {
        given:
        def file =  Stub(MultipartFile)
        when:
        def id = imageFacade.save(file)
        def returned = imageFacade.get(id)
        then:
        returned != null
    }

}

