package pl.ecommerce.backend.message.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CreateFinalizeSaleMessageDto {
    Long buyerId;
    Long sellerId;
    String productName;
    BigDecimal amount;
}
