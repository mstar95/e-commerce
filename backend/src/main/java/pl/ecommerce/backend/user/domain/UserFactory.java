package pl.ecommerce.backend.user.domain;

import lombok.experimental.UtilityClass;
import pl.ecommerce.backend.user.dto.CreateUserDto;
import pl.ecommerce.backend.user.exceptions.CreateUserException;

@UtilityClass
class UserFactory {

    User createUser(CreateUserDto createUserDto){
        if(!createUserDto.getPassword().equals(createUserDto.getRePassword())){
            throw new CreateUserException("Passwords not match");
        }
        return User.builder()
                .name(createUserDto.getName())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail()).build();
    }

}
