package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.FinalizeSaleDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SaleFacade {

    private final SaleService saleService;
    private final SalePaymentsService salePaymentsService;

    public Long createSale(SaleInDto saleInDto) {
        Sale sale = SaleFactory.createSale(saleInDto);
        return saleService.createSale(sale);
    }

    public Long createSaleOfNewProduct(SaleInDto saleInDto, ProductDto productDto) {
        Sale sale = SaleFactory.createSale(saleInDto, productDto);
        return saleService.createSaleOfNewProduct(sale, productDto);
    }

    public Optional<ArchivedSaleDto> finalizeSale(FinalizeSaleDto finalizeSaleDto) {
        return salePaymentsService.finalizeSale(finalizeSaleDto);
    }

    public Optional<SaleOutDto> find(Long saleId) {
        return saleService.find(saleId);
    }

    public List<SaleOutDto> findByUserId(long userId) {
        return saleService.findByUserId(userId);
    }
}
