package pl.ecommerce.backend.payment.domain;

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
class Wallet {
    @Id
    private Long id;
    private Long userId;
    private BigDecimal points;
}
