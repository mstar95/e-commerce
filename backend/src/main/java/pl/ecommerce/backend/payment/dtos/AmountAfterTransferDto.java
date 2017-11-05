package pl.ecommerce.backend.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class AmountAfterTransferDto {
    private Long fromId;
    private Long toId;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
}
