package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@Entity
class Sale {
    private Long id;
    private Long userId;
    private Long productId;
}
