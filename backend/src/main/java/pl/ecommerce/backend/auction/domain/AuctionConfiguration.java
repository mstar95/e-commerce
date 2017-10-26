package pl.ecommerce.backend.auction.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.user.domain.UserFacade;

@Configuration
class AuctionConfiguration {

    @Bean
    AuctionFacade auctionFacade(AuctionRepository auctionRepository,
                                ProductFacade productFacade,
                                UserFacade userFacade){
        AuctionService auctionService = new AuctionService(auctionRepository, productFacade, userFacade);
        return new AuctionFacade(auctionService);
    }
}
