package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
class AuctionService {
    private final SalePaymentsService salePaymentsService;
    private final SaleRepository saleRepository;
    private final PaymentFacade paymentFacade;
    private final UserFacade userFacade;

    boolean bidAuction(Long id, BigDecimal amount) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        if (sale.isBuyNow()){
            throw new SaleFindException("Sale with id: " + id + " is not auction");
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

    Optional<ArchivedSaleDto> finalizeAuction(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + id));
        if (sale.isBuyNow()){
            throw new SaleFindException("Sale with id: " + id + "is not a auction");
        }
        Long winnerId = sale.getWinnerId();
        paymentFacade.transferPointsFromLock(SaleFactory.createTransferPointsDto(sale, winnerId));
        ArchivedSale archivedSale = salePaymentsService.prepareAndSaveArchivedSale(sale, winnerId);
        saleRepository.deleteById(sale.getId());
        return Optional.of(SaleFactory.createArchivedSaleDto(archivedSale));
    }
}
