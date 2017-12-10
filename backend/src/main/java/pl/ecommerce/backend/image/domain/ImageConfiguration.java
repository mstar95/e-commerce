package pl.ecommerce.backend.image.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ImageConfiguration {

    @Bean
    ImageFacade imageFacade(ImageRepository imageRepository){
        ImageService imageService = new ImageService(imageRepository);
        return new ImageFacade(imageService);
    }
}
