package pl.ecommerce.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.message.domain.MessageFacade;
import pl.ecommerce.backend.message.dto.CreateFinalizeSaleMessageDto;
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
    private final MessageFacade messageFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long user1Id = userFacade.createUser(CreateUserDto.builder().name("buyer").password("buyer")
                .rePassword("buyer").email("buyer").build());
        Long user2Id = userFacade.createUser(CreateUserDto.builder().name("seller").password("seller")
                .rePassword("seller").email("seller").build());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("seller", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        saleFacade.createSale(CreateSaleDto.builder().buyNow(false)
            .deadline(LocalDateTime.now()).imageId(null).description("Bardzo fajny piesek, przystepkna cena")
                .name("Piesek").price(BigDecimal.ONE).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Swietny rower")
                .name("Rower").price(BigDecimal.TEN).build());
        messageFacade.createFinalizeSaleMessage(CreateFinalizeSaleMessageDto.builder()
                .buyerId(user1Id).sellerId(user2Id).productName("Lego").amount(BigDecimal.TEN).build());
    }
}
