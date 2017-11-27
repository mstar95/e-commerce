package pl.ecommerce.backend.user.domain;

import lombok.experimental.UtilityClass;
import pl.ecommerce.backend.user.dto.CreateUserDto;
import pl.ecommerce.backend.user.query.UserOutDto;

@UtilityClass
class UserFactory {

    User createUser(CreateUserDto createUserDto){
        return User.builder()
                .name(createUserDto.getName())
                .password(createUserDto.getPassword())
                .rating(0L).build();
    }

}
