package pl.ecommerce.backend.user.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class UserOutDto {
    private String name;
    private BigDecimal points;
}
