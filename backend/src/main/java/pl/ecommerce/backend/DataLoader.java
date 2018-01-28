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
import pl.ecommerce.backend.user.query.QueryUserProfileRepository;

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
    private final QueryUserProfileRepository queryUserProfileRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
   /*   Long user1Id = userFacade.createUser(CreateUserDto.builder().name("user").password("buyer")
                .rePassword("buyer").email("buyer").build());
        Long user2Id = userFacade.createUser(CreateUserDto.builder().name("user2").password("seller")
                .rePassword("seller").email("seller").build());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("user2", null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("MACIEJ MALEŃCZUK: MALEŃCZUK GRA MŁYNARSKIEGO [CD]")
                .name("MACIEJ MALEŃCZUK: MALEŃCZUK GRA MŁYNARSKIEGO [CD]").price(new BigDecimal(40)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Kabel HDMI - HDMI 3m 3D - 4K FULL HD gwarancja")
                .name("Kabel HDMI - HDMI 3m 3D - 4K FULL HD gwarancja").price(BigDecimal.TEN).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Prostownica do włosów Remington S8590 GW 5+1")
                .name("Prostownica do włosów Remington S8590 GW 5+1").price(BigDecimal.TEN).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Głośnik przenośny JBL Flip 3 Deep Black Bluetooth")
                .name("Głośnik przenośny JBL Flip 3 Deep Black Bluetooth ").price(BigDecimal.TEN).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("DEPECHE MODE: SPIRIT [CD] NAJNOWSZA 2017 !!!")
                .name("DEPECHE MODE: SPIRIT [CD] NAJNOWSZA 2017 !!!").price(new BigDecimal(50)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("MICHAŁ SZPAK: BYLE BYĆ SOBĄ CD Color Of Your Life")
                .name("MICHAŁ SZPAK: BYLE BYĆ SOBĄ CD Color Of Your Life").price(new BigDecimal(30)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("ED SHEERAN: DIVIDE (DELUXE EDITION) [CD] HIT! 2017")
                .name("ED SHEERAN: DIVIDE (DELUXE EDITION) [CD] HIT! 2017").price(new BigDecimal(35)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("CZESŁAW NIEMEN: ZŁOTA KOLEKCJA [CD] SZYBKO!!!")
                .name("CZESŁAW NIEMEN: ZŁOTA KOLEKCJA [CD] SZYBKO!!!").price(new BigDecimal(40)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("COLDPLAY: A HEAD FULL OF DREAMS (CD) OD RĘKI !!!")
                .name("COLDPLAY: A HEAD FULL OF DREAMS (CD) OD RĘKI !!!").price(new BigDecimal(40)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("ED SHEERAN: DIVIDE (DELUXE EDITION) [CD] HIT! 2017")
                .name("ED SHEERAN: DIVIDE CDs HIT! 2017").price(new BigDecimal(35)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(false)
                .deadline(LocalDateTime.now()).imageId(null).description("SŁUCHAWKI JBL NAUSZNE CZARNE")
                .name("SŁUCHAWKI JBL NAUSZNE CZARNE").price(new BigDecimal(140)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(false)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Buty Reebok ROYAL CLJOG")
                .name("Buty Reebok ROYAL CLJOG").price(new BigDecimal(120)).build());
        saleFacade.createSale(CreateSaleDto.builder().buyNow(true)
                .deadline(LocalDateTime.now().minusDays(1)).imageId(null).description("Termoaktywne leginsy damskie, wykonane z wysokiej jakości mieszanki polipropylenu, poliamidu i elastyny. Stworzone z użyciem technologii COOL i FIT ZONE usprawniających oddychanie materiału oraz zapewniających wysoki komfort podczas użytkowania. Dodatkowo użyto rewolucyjnego systemu POLYGIENE zapewniającego ochronę przed bakteriami i nieprzyjemnym zapachem.")
                .name("HERO BASELAYER WOMEN’S 3/4 LEGGINGS").price(new BigDecimal(120)).build());
        queryUserProfileRepository.findAll();
        messageFacade.createFinalizeSaleMessage(CreateFinalizeSaleMessageDto.builder()
                .buyerId(user1Id).sellerId(user2Id).productName("Zestaw do sprzątania").amount(BigDecimal.TEN).build()); */

    }

}
