package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Entity
class Sale {
    @Id
    private Long id;
    private Long userId;
    private Long productId;
    private BigDecimal price;
}
