package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.message.domain.MessageFacade;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.Optional;

@RequiredArgsConstructor
class SalePaymentsService {
    private final SaleRepository saleRepository;
    private final ElasticSearchSaleRepository elasticSearchSaleRepository;
    private final PaymentFacade paymentFacade;
    private final UserFacade userFacade;
    private final MessageFacade messageFacade;

    Optional<Long> finalizeSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        if (!sale.isBuyNow()){
            throw new SaleFindException("Sale with id: " + id + "is auction");
        }
        Long currentUserId = userFacade.getCurrentUserId();
        paymentFacade.transferPoints(SaleFactory.createTransferPointsDto(sale, currentUserId));
        saleRepository.deleteById(sale.getId());
        elasticSearchSaleRepository.deleteByEntityId(sale.getId());
        messageFacade.createFinalizeSaleMessage(SaleFactory.createFinalizeSaleMessage(sale, currentUserId));
        return Optional.of(id);
    }

}
