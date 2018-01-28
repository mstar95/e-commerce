package pl.ecommerce.backend.payment.dtos;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class ChargePointsDto {
    @NotNull
    private BigDecimal amount;
}
