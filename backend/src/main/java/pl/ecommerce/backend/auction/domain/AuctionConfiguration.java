package pl.ecommerce.backend.auction.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.product.domain.ProductFacade;

@Configuration
class AuctionConfiguration {

    @Bean
    AuctionFacade auctionFacade(AuctionRepository auctionRepository,
                                ProductFacade productFacade){
        AuctionService auctionService = new AuctionService(auctionRepository, productFacade);
        return new AuctionFacade(auctionService);
    }
}
