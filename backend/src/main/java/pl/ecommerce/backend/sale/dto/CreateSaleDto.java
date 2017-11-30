package pl.ecommerce.backend.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CreateSaleDto {
    private Long id;
    private String name;
    private byte[] image;
    private BigDecimal price;
}