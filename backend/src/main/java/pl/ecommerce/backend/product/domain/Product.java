package pl.ecommerce.backend.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@Entity
class Product {
    @Id
    private Long id;
    private Long userId;
    private String name;
}