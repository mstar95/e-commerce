package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

@RequiredArgsConstructor
class SaleService {

    private final SaleRepository saleRepository;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    Long createSale(Sale sale) {
        sale.setUserId(userFacade.getCurrentUserId());
        sale.setCreated(timeManager.getCurrentTimestamp());
        return saleRepository.save(sale).getId();
    }

    Long createAuction(Sale sale) {
        sale.setUserId(userFacade.getCurrentUserId());
        return saleRepository.save(sale).getId();
    }
}
