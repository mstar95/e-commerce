package pl.ecommerce.backend.product.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ProductService {

    private final ProductRepository productRepository;

    Long createProduct(Product product) {
       return productRepository.save(product).getId();
    }
}
