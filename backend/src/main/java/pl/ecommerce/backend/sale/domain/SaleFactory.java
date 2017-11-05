package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class SaleFactory {

    static Sale createSale(SaleInDto saleInDto) {
        return Sale.builder()
                .id(saleInDto.getId())
                .productId(saleInDto.getProductId())
                .price(saleInDto.getPrice())
                .build();
    }

    static SaleOutDto createSaleOutDto(Sale sale, ProductDto productDto) {
        return SaleOutDto.builder()
                .id(sale.getId())
                .userId(sale.getUserId())
                .productId(productDto.getId())
                .name(productDto.getName())
                .price(sale.getPrice())
                .build();
    }

    static Sale createSale(SaleInDto saleInDto, ProductDto productDto) {
        return Sale.builder()
                .id(saleInDto.getId())
                .userId(productDto.getUserId())
                .productId(saleInDto.getProductId())
                .price(saleInDto.getPrice())
                .build();
    }

    static TransferPointsDto createTransferPointsDto(Sale sale, Long fromId){
        return TransferPointsDto.builder()
                .fromId(fromId)
                .toId(sale.getId())
                .amount(sale.getPrice())
                .build();
    }

    static ArchivedSale createArchivedSale(Sale sale, Long clientId, LocalDateTime transactionDate){
        return ArchivedSale.builder()
                .oldId(sale.getId())
                .ownerId(sale.getUserId())
                .clientId(clientId)
                .price(sale.getPrice())
                .productId(sale.getProductId())
                .transactionDate(Timestamp.valueOf(transactionDate))
                .build();
    }

    static ArchivedSaleDto createArchivedSaleDto(ArchivedSale sale, ProductDto productDto){
        return ArchivedSaleDto.builder()
                .oldId(sale.getOldId())
                .clientId(sale.getClientId())
                .ownerId(sale.getOwnerId())
                .price(sale.getPrice())
                .productId(sale.getProductId())
                .name(productDto.getName())
                .transactionDate(sale.getTransactionDate().toLocalDateTime())
                .build();
    }

}
