package pl.ecommerce.backend.user.dto;

import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Getter
public class UserOutDto {
    private String name;
    private BigDecimal points;
}
