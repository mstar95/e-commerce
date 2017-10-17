package pl.ecommerce.backend.product.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class ProductService {

    private final ProductRepository productRepository;

    Long createProduct(Product product) {

        return productRepository.save(product).getId();
    }

    Optional<Product> find(Long productId) {
        return productRepository.findById(productId);
    }
}
