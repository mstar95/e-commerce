package pl.ecommerce.backend.sale.domain;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

@RequiredArgsConstructor
class SaleService {

    private final SaleRepository saleRepository;
    private final ElasticSearchSaleRepository elasticSearchSaleRepository;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    Long createSale(Sale sale) {
        sale.setUserId(userFacade.getCurrentUserId());
        sale.setCreated(timeManager.getCurrentTimestamp());
        Sale savedSale = saleRepository.save(sale);
      //  createElasticSearchSale(savedSale);
        return savedSale.getId();
    }

    private void createElasticSearchSale(Sale savedSale){
        ElasticSearchSale elasticSearchSale = ElasticSearchSaleFactory.createElasticSearchSale(savedSale);
        elasticSearchSaleRepository.index(elasticSearchSale);
    }

    SaleDetailDto findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        Long currentUserId = Try.of(userFacade::getCurrentUserId).getOrNull();
        boolean isOwner = sale.getUserId().equals(currentUserId);
        boolean isWinner = sale.getWinnerId() != null && sale.getWinnerId().equals(currentUserId);
        return SaleFactory.createSaleDetailDto(sale, isOwner, isWinner);
    }
}
