package pl.ecommerce.backend.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    private String name;
    private String password;
    private String rePassword;
    private String email;
}
