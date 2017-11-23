package pl.ecommerce.backend.security;

import org.springframework.data.repository.Repository;

interface UserCredentialsRepository extends Repository<UserCredentials, Long> {
    UserCredentials getUserCredentialsByName(String name);
}
