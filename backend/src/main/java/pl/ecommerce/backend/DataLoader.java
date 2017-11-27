package pl.ecommerce.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.dto.CreateUserDto;

@Slf4j
@Component
@RequiredArgsConstructor
class DataLoader implements ApplicationRunner {

    private final UserFacade userFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       userFacade.createUser(CreateUserDto.builder().name("user").password("user").build());
    }

}
