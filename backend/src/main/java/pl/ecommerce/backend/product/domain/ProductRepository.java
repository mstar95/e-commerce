package pl.ecommerce.backend.product.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface ProductRepository extends Repository<Product, Long> {
    Product save(Product entity);
    Optional<Product> findById(Long id);
}

