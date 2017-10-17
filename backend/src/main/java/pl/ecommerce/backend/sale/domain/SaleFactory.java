package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

class SaleFactory {

    static Sale createSale(SaleInDto saleInDto) {
        return Sale.builder()
                .id(saleInDto.getId())
                .productId(saleInDto.getProductId())
                .build();
    }

    static SaleOutDto createSaleOutDto(Sale sale, ProductDto productDto) {
        return SaleOutDto.builder()
                .id(sale.getId())
                .userId(sale.getUserId())
                .productId(productDto.getId())
                .name(productDto.getName())
                .build();
    }

    static Sale createSale(SaleInDto saleInDto, ProductDto productDto) {
        return Sale.builder()
                .id(saleInDto.getId())
                .userId(productDto.getUserId())
                .productId(saleInDto.getProductId())
                .build();
    }
}
