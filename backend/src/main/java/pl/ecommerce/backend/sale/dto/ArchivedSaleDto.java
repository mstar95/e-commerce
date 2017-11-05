package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ArchivedSaleDto {
    private Long id;
    private Long oldId;
    private Long ownerId;
    private Long clientId;
    private Long productId;
    private String name;
    private BigDecimal price;
    private LocalDateTime transactionDate;
}
