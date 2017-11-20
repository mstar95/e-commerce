package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;

import java.util.Optional;

@RequiredArgsConstructor
public class SaleFacade {

    private final SaleService saleService;
    private final SalePaymentsService salePaymentsService;

    public Long createSale(CreateSaleDto createSaleDto) {
        Sale sale = SaleFactory.createSale(createSaleDto);
        return saleService.createSale(sale);
    }

    public Long createAuction(CreateSaleDto createSaleDto) {
        Sale sale = SaleFactory.createSale(createSaleDto);
        return saleService.createAuction(sale);
    }

    public Optional<ArchivedSaleDto> finalizeSale(Long id) {
        return salePaymentsService.finalizeSale(id);
    }

}
