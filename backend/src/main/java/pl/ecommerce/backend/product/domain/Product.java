package pl.ecommerce.backend.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
class Product {
    private Long id;
    private Long userId;
    private String name;
}