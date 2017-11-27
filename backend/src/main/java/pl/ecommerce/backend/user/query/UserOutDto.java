package pl.ecommerce.backend.user.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserOutDto {
    private String name;
    private Long rating;
    private BigDecimal points;
}
