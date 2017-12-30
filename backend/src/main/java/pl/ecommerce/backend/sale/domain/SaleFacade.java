package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.sale.dto.BidAuctionDto;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;

import java.util.Optional;

@RequiredArgsConstructor
public class SaleFacade {

    private final SaleService saleService;
    private final SalePaymentsService salePaymentsService;
    private final AuctionService auctionService;

    public Long createSale(CreateSaleDto createSaleDto) {
        Sale sale = SaleFactory.createSale(createSaleDto);
        return saleService.createSale(sale);
    }

    public Optional<Long> finalizeSale(Long id) {
        return salePaymentsService.finalizeSale(id);
    }

    public boolean bidAuction(BidAuctionDto bidAuctionDto){
        return auctionService.bidAuction(bidAuctionDto.getAuctionId(), bidAuctionDto.getAmount());
    }

    public void finalizeAuctions(){
        auctionService.finalizeAuctions();
    }

    public SaleDetailDto findById(Long id) {
       return saleService.findById(id);
    }

}
