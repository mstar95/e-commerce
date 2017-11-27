package pl.ecommerce.backend.user.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.user.dto.CreateUserDto;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public Long createUser(CreateUserDto createUserDto) {
        return userService.createUser(UserFactory.createUser(createUserDto));
    }

    public String getCurrentUserName() {
        return userService.getCurrentUserName();
    }

    public Long getCurrentUserId() {
        return userService.getCurrentUserId();
    }
}
