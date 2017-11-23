package pl.ecommerce.backend.user;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.dto.CreateUserDto;
import pl.ecommerce.backend.user.query.QueryUser;
import pl.ecommerce.backend.user.query.QueryUserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserFacade userFacade;
    private final QueryUserRepository queryUserRepository;

    @PostMapping(value = "/create")
    public ResponseEntity<Long> create(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userFacade.createUser(createUserDto));

    }

    @GetMapping(value = "/all")
    public ResponseEntity<QueryUser> all() {
      /*  return Try.of(querySaleRepository::findAll)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.badRequest().body(null)); */
        return  ResponseEntity.ok(queryUserRepository.findUser(1L));
    }
}
