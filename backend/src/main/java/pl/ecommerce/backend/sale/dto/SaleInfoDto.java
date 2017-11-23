package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class SaleInfoDto {
    private Long id;
    private byte[] image;
    private String name;
    private Long userId;
    private BigDecimal price;
    private LocalDateTime created;
}
