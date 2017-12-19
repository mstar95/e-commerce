package pl.ecommerce.backend.payment.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class TransferPointsDto {
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
}
