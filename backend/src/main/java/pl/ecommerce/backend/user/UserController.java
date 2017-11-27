package pl.ecommerce.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.dto.CreateUserDto;
import pl.ecommerce.backend.user.query.UserOutDto;
import pl.ecommerce.backend.user.query.QueryUserProfileRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserFacade userFacade;
    private final QueryUserProfileRepository queryUserRepository;

    @PostMapping(value = "/create")
    public ResponseEntity<Long> create(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userFacade.createUser(createUserDto));

    }

    @GetMapping(value = "/current")
    public ResponseEntity<UserOutDto> current() {
        String name = userFacade.getCurrentUserName();
        return ResponseEntity.ok(queryUserRepository.findQueryUserByName(name));
    }
}
