package pl.ecommerce.backend.search.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SearchFacade {

    private final SearchService searchService;

    public List<SaleOutDto> getAuctionsAndSales(){
        //return searchService.getAuctionsAndSales();
        return Arrays.asList(SaleOutDto.builder().id(0L).name("Yeezy").build(),
                SaleOutDto.builder().id(1L).name("Nike").build(),
                SaleOutDto.builder().id(2L).name("Volvo").build());
    }

}
