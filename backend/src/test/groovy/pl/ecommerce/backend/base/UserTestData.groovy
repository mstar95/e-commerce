package pl.ecommerce.backend.base

import pl.ecommerce.backend.user.dto.CreateUserDto


class UserTestData {
    static Long USER_ID_1 = 1L
    static Long USER_ID_2 = 2L

    static CreateUserDto createUserDto = new CreateUserDto("user", "password")

}
