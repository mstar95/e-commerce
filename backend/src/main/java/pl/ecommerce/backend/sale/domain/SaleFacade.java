package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SaleFacade {

    private final SaleService saleService;

    public Long createSale(SaleInDto saleInDto) {
        Sale sale = SaleFactory.createSale(saleInDto);
        return saleService.createSale(sale);
    }

    public Long createSaleOfNewProduct(SaleInDto saleInDto, ProductDto productDto) {
        Sale sale = SaleFactory.createSale(saleInDto);
        return saleService.createSaleOfNewProduct(sale, productDto);
    }

    public Optional<SaleOutDto> find(Long saleId) {
        return saleService.find(saleId);
    }

    public List<SaleOutDto> findByUserId(long userId) {
        return saleService.findByUserId(userId);
    }
}
