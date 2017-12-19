package pl.ecommerce.backend.payment.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ChargePointsDto {
    private BigDecimal amount;
}
