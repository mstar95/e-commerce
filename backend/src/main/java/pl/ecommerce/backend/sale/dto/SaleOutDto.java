package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SaleOutDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String name;
    private BigDecimal price;
}
