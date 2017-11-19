package pl.ecommerce.backend.search.domain;


import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.sale.domain.SaleFacade;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.List;

@RequiredArgsConstructor
class SearchService {

    private final SaleFacade saleFacade;

    List<SaleOutDto> getAuctionsAndSales() {
        return saleFacade.getSales();
    }
}
