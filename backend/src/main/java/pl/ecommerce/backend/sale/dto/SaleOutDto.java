package pl.ecommerce.backend.sale.dto;

import lombok.*;

import java.math.BigDecimal;

@Value
@Getter
@Builder
@AllArgsConstructor
public class SaleOutDto {
    private Long id;
    private Long imageId;
    private String name;
    private BigDecimal price;
    private Boolean buyNow;
}
