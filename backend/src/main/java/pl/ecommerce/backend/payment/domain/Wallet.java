package pl.ecommerce.backend.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
class Wallet {
    private Long id;
    private Long userId;
    private BigDecimal points;
}
