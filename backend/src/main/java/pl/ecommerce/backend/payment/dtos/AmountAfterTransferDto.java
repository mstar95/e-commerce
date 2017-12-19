package pl.ecommerce.backend.payment.dtos;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AmountAfterTransferDto {
    private Long fromId;
    private Long toId;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
}
