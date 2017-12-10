package pl.ecommerce.backend.image.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface ImageRepository extends Repository<Image, Long> {

    Image save(Image entity);

    Optional<Image> getImageById(Long id);
}
