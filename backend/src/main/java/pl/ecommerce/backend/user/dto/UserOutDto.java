package pl.ecommerce.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDto {
    private String name;
    private Long rating;
    private BigDecimal points;
}
