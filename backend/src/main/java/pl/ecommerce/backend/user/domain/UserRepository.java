package pl.ecommerce.backend.user.domain;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User,Long> {
    User save(User entity);
}
