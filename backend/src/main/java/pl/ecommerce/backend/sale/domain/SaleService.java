package pl.ecommerce.backend.sale.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.product.domain.ProductFacade;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.exceptions.SaleCreationException;
import pl.ecommerce.backend.user.domain.UserFacade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class SaleService {

    private final SaleRepository saleRepository;
    private final ProductFacade productFacade;
    private final UserFacade userFacade;

    Long createSale(Sale sale) {
        ProductDto productDto = getProductById(sale.getProductId());
        validateAndSetUserId(sale, productDto);
        return saleRepository.save(sale).getId();
    }

    Long createSaleOfNewProduct(Sale sale, ProductDto productDto) {
        Long productId = productFacade.createProduct(productDto);
        sale.setProductId(productId);
        sale.setUserId(userFacade.getCurrentUserId());
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

    List<SaleOutDto> findByUserId(long userId) {
        List<Sale> sales = saleRepository.findSalesByUserId(userId);
        return sales.stream()
                .map(this::getProductByIdAndMergeWithSale)
                .collect(Collectors.toList());
    }

    private void validateAndSetUserId(Sale sale, ProductDto productDto) {
        Long currentUserId = userFacade.getCurrentUserId();
        if (!productDto.getUserId().equals(currentUserId)) {
            throw new SaleCreationException("Current user is not owner of product with id:" + productDto.getId());
        }
        sale.setUserId(productDto.getUserId());
    }

    private SaleOutDto getProductByIdAndMergeWithSale(Sale sale){
        ProductDto productDto = getProductById(sale.getProductId());
        return SaleFactory.createSaleOutDto(sale, productDto);
    }

    private ProductDto getProductById(Long productId) {
        return productFacade.find(productId)
                .orElseThrow(() -> new SaleCreationException("Product with id " + productId + " not exist"));
    }

    public List<SaleOutDto> getSales() {
        return saleRepository.findAll().stream()
                .map(this::getProductByIdAndMergeWithSale)
                .collect(Collectors.toList());
    }
}
