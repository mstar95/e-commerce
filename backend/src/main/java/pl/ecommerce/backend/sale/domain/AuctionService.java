package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.math.BigDecimal;

@RequiredArgsConstructor
class AuctionService {
    private final SaleRepository saleRepository;
    private final PaymentFacade paymentFacade;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    boolean bidAuction(Long id, BigDecimal amount) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        if (sale.isBuyNow()){
            throw new SaleFindException("Sale with id: " + id + " is not an auction");
        }
        Long currentUserId = userFacade.getCurrentUserId();
        paymentFacade.lockPoints(SaleFactory.createLockPointsDto(amount, currentUserId));
        if (sale.getWinnerId() != null) {
            paymentFacade.unLockPoints(SaleFactory.createLockPointsDto(sale.getPrice(), currentUserId));
        }
        sale.setWinnerId(currentUserId);
        sale.setPrice(amount);
        saleRepository.save(sale);
        return true;

    }

    void finalizeAuctions() {
       saleRepository.findSalesByDeadlineAfterAndBuyNowIsFalse(timeManager.getCurrentTimestamp())
               .forEach(this::finalizeAuction);
    }

    private void finalizeAuction(Sale sale) {
        if (sale.isBuyNow()) {
            throw new SaleFindException("Sale with id: " + sale.getId() + "is not a auction");
        }
        Long winnerId = sale.getWinnerId();
        if (winnerId != null) {
            paymentFacade.transferPointsFromLock(SaleFactory.createTransferPointsDto(sale, winnerId));
        }
        saleRepository.deleteById(sale.getId());
    }
}
