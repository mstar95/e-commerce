package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.FinalizeSaleDto;
import pl.ecommerce.backend.sale.exceptions.FinalizeSaleException;
import pl.ecommerce.backend.sale.exceptions.SaleCreationException;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
class SalePaymentsService {
    private final SaleRepository saleRepository;
    private final ArchivedSaleRepository archivedSaleRepository;
    private final PaymentFacade paymentFacade;
    private final ProductFacade productFacade;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    Optional<ArchivedSaleDto> finalizeSale(FinalizeSaleDto finalizeSaleDto) {
        Sale sale = saleRepository.findById(finalizeSaleDto.getId())
                .orElseThrow(() -> new SaleFindException("There is no sale with id:" + finalizeSaleDto.getId()));

        Long currentUserId = userFacade.getCurrentUserId();
        Integer amount = finalizeSaleDto.getAmount();
        paymentFacade.transferPoints(SaleFactory.
                createTransferPointsDto(sale, currentUserId,calculatePrice(sale, amount)));
        ArchivedSale archivedSale = prepareAndSaveArchivedSale(sale, currentUserId, amount);
        calculateAmountAndDeleteIfZero(sale, amount);
        ProductDto productDto = getProductById(sale.getProductId());

        return Optional.of(SaleFactory.createArchivedSaleDto(archivedSale, productDto));
    }

    private ArchivedSale prepareAndSaveArchivedSale(Sale sale, Long currentUserId, Integer amount) {
        ArchivedSale archivedSale = SaleFactory.createArchivedSale(sale, currentUserId,
                timeManager.getCurrentDate(), amount);
        return archivedSaleRepository.save(archivedSale);
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new SaleCreationException("Product with id " + productId + " not exist"));
    }

    private BigDecimal calculatePrice(Sale sale, Integer amount) {
        if (sale.getAmount() < amount) {
            throw new FinalizeSaleException("sale has amount " + sale.getAmount()
                    + " and user want to buy amount of " + amount);
        }
        return sale.getPrice().multiply(new BigDecimal(amount));
    }

    private void calculateAmountAndDeleteIfZero(Sale sale, Integer amount){
        sale.setAmount(sale.getAmount() - amount);
        if (sale.getAmount().equals(0)) {
            saleRepository.deleteById(sale.getId());
        }
    }
}
