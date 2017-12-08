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
    private Long imageId;
    private String name;
    private BigDecimal price;
    private Boolean buyNow;
}
