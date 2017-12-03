package pl.ecommerce.backend.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User,Long> {
    User save(User entity);
    Optional<User> getUserByName(String name);
    Optional<User> getUserByNameOrEmail(String name, String email);
}
