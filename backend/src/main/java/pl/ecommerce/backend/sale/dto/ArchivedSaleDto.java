package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
@Builder
public class ArchivedSaleDto {
    private Long id;
    private Long imageId;
    private String name;
    private BigDecimal price;
    private Boolean buyNow;
    private Long ownerId;
    private Long clientId;
    private Timestamp transactionDate;
}
