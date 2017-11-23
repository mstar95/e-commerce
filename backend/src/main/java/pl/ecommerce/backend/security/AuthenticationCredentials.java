package pl.ecommerce.backend.security;

import lombok.Data;

@Data
class AuthenticationCredentials {
    private String username;
    private String password;
}
