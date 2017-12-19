package pl.ecommerce.backend.sale.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class CreateSaleDto {
    private String name;
    private Long imageId;
    private String description;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime deadline;
    private boolean buyNow;
}
