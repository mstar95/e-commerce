package pl.ecommerce.backend.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class TransferPointsDto {
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
}
