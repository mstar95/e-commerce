package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.auction.dto.AuctionOutDto;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.exceptions.SaleCreationException;

import java.util.Optional;

@RequiredArgsConstructor
class SaleService {

    private final SaleRepository saleRepository;
    private final ProductFacade productFacade;

    Long createSale(Sale sale) {
        getProductById(sale.getProductId());
        return saleRepository.save(sale).getId();
    }

    Long createSaleOfNewProduct(Sale sale, ProductDto productDto) {
        Long productId = productFacade.createProduct(productDto);
        sale.setProductId(productId);
        return saleRepository.save(sale).getId();
    }

    Optional<SaleOutDto> find(Long saleId) {
        Optional<Sale> saleOpt = saleRepository.findById(saleId);
        if(!saleOpt.isPresent()) {
            return Optional.empty();
        }
        Sale sale = saleOpt.get();
        ProductDto productDto = getProductById(sale.getProductId());
        return Optional.of(SaleFactory.createSaleOutDto(sale, productDto));
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new SaleCreationException("Product with id " + productId + " not exist"));
    }
}
