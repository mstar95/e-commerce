package pl.ecommerce.backend.image.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class ImageFacade {

    private final ImageService imageService;

    public Long save(MultipartFile file) {
        return imageService.save(file);
    }

    public byte[] get(Long id) {
        return imageService.get(id);
    }

}
