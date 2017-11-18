package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SaleInDto {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private boolean multi;
    private Integer amount;
}
