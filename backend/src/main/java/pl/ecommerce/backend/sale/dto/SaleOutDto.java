package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SaleOutDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String name;
}
