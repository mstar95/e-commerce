package pl.ecommerce.backend.security;

import lombok.Data;

@Data
class JwtAuthenticationResponse {
    final String token;
}
