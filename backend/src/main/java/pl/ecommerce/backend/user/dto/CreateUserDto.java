package pl.ecommerce.backend.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {
    private String name;
    private String password;
}
