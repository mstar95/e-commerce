package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class ArchivedSaleDto {
    private Long id;
    private byte[] image;
    private String name;
    private BigDecimal price;
    private Boolean buyNow;
    private Long ownerId;
    private Long clientId;
    private Timestamp transactionDate;
}
