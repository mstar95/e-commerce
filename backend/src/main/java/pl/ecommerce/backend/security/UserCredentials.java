package pl.ecommerce.backend.security;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Table(name = "user")
class UserCredentials {
    @Id
    private Long id;
    private String name;
    private String password;
}
