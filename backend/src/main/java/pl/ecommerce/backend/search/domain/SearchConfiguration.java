package pl.ecommerce.backend.search.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ecommerce.backend.sale.domain.SaleFacade;

@Configuration
class SearchConfiguration {
    @Bean
    SearchFacade searchFacade(SaleFacade saleFacade){
        SearchService searchService = new SearchService(saleFacade);
        return new SearchFacade(searchService);
    }
}
