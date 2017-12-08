package pl.ecommerce.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.user.domain.UserFacade;
import pl.ecommerce.backend.user.dto.CreateUserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
class DataLoader implements ApplicationRunner {

    private final UserFacade userFacade;
    private final SaleFacade saleFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userFacade.createUser(CreateUserDto.builder().name("user").password("user")
                .rePassword("user").email("XD").build());
        userFacade.createUser(CreateUserDto.builder().name("a").password("a")
                .rePassword("a").email("a").build());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        saleFacade.createSale(CreateSaleDto.builder().buyNow(false)
            .deadline(LocalDateTime.now()).imageId(null).description("XD")
                .name("XDD").price(BigDecimal.ONE).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description(":D")
                .name(":DD").price(BigDecimal.TEN).build());
    }

}
