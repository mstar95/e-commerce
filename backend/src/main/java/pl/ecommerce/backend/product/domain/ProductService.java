package pl.ecommerce.backend.product.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.Optional;

@RequiredArgsConstructor
class ProductService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    Long createProduct(Product product) {
        Long userId = userFacade.getCurrentUserId();
        product.setUserId(userId);
        return productRepository.save(product).getId();
    }

    Optional<Product> find(Long productId) {
        return productRepository.findById(productId);
    }
}
