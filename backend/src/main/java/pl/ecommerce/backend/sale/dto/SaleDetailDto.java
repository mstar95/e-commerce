package pl.ecommerce.backend.sale.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class SaleDetailDto {
    private Long id;
    private String name;
    private Long imageId;
    private String description;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime deadline;
    private boolean buyNow;
    private boolean isOwner;
    private boolean isWinner;
}
