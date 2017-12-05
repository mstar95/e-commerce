package pl.ecommerce.backend.payment.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LockPointsDto {
    private long userId;
    private BigDecimal amount;
}
