package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.Optional;

@RequiredArgsConstructor
class SalePaymentsService {
    private final SaleRepository saleRepository;
    private final ArchivedSaleRepository archivedSaleRepository;
    private final PaymentFacade paymentFacade;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    Optional<ArchivedSaleDto> finalizeSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        if (!sale.isBuyNow()){
            throw new SaleFindException("Sale with id: " + id + "is auction");
        }
        Long currentUserId = userFacade.getCurrentUserId();
        paymentFacade.transferPoints(SaleFactory.createTransferPointsDto(sale, currentUserId));
        ArchivedSale archivedSale = prepareAndSaveArchivedSale(sale, currentUserId);
        saleRepository.deleteById(sale.getId());
        return Optional.of(SaleFactory.createArchivedSaleDto(archivedSale));
    }


    ArchivedSale prepareAndSaveArchivedSale(Sale sale, Long currentUserId) {
        ArchivedSale archivedSale = SaleFactory.createArchivedSale(sale, currentUserId,
                timeManager.getCurrentDate());
        return archivedSaleRepository.save(archivedSale);
    }
}
