package pl.ecommerce.backend.image.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
class ImageService {

    private final ImageRepository imageRepository;

    Long save(MultipartFile file) {
        try {
            Image image = Image.builder().file(file.getBytes()).build();
            return imageRepository.save(image).getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    byte[] get(Long id) {
      return imageRepository.getImageById(id).get().getFile();
    }

}
