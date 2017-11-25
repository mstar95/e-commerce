package pl.ecommerce.backend.security;

import lombok.Data;

@Data
class JwtAuthenticationRequest {
    String username;
    String password;
}
