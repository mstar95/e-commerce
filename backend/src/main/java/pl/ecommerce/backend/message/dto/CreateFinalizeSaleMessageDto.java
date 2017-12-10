package pl.ecommerce.backend.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CreateFinalizeSaleMessageDto {
    Long buyerId;
    Long sellerId;
    String productName;
    BigDecimal amount;
}
