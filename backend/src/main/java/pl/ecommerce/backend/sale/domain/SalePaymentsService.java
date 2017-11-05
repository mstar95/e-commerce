package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.exceptions.SaleCreationException;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.time.domain.TimeManager;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.Optional;

@RequiredArgsConstructor
class SalePaymentsService {
    private final SaleRepository saleRepository;
    private final ArchivedSaleRepository archivedSaleRepository;
    private final PaymentFacade paymentFacade;
    private final ProductFacade productFacade;
    private final UserFacade userFacade;
    private final TimeManager timeManager;

    Optional<ArchivedSaleDto> finalizeSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(()-> new SaleFindException("There is no sale with id:" + saleId));

        Long currentUserId = userFacade.getCurrentUserId();
        paymentFacade.transferPoints(SaleFactory.createTransferPointsDto(sale, currentUserId));
        ArchivedSale archivedSale = prepareAndSaveArchivedSale(sale, currentUserId);
        saleRepository.deleteById(sale.getId());
        ProductDto productDto = getProductById(sale.getProductId());

        return Optional.of(SaleFactory.createArchivedSaleDto(archivedSale, productDto));
    }

    private ArchivedSale prepareAndSaveArchivedSale(Sale sale, Long currentUserId){
        ArchivedSale archivedSale = SaleFactory.createArchivedSale(sale, currentUserId,
                timeManager.getCurrentDate());
        return archivedSaleRepository.save(archivedSale);
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new SaleCreationException("Product with id " + productId + " not exist"));
    }
}
