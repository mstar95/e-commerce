package pl.ecommerce.backend.image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ecommerce.backend.image.domain.ImageFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {

    private final ImageFacade imageFacade;

    @PostMapping()
    public ResponseEntity<Long> handleFileUpload(@RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(imageFacade.save(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> handleFileUpload(@PathVariable("id") long id) {

        return ResponseEntity.ok(imageFacade.get(id));
    }
}
