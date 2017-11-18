package pl.ecommerce.backend.sale.domain;

import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.product.dto.ProductDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.SaleInDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;
import pl.ecommerce.backend.sale.exceptions.SaleInDtoArgumentsException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class SaleFactory {

    static Sale createSale(SaleInDto saleInDto) {
        return createSaleBuilder(saleInDto)
                .build();
    }

    static Sale createSale(SaleInDto saleInDto, ProductDto productDto) {
        return createSaleBuilder(saleInDto)
                .userId(productDto.getUserId())
                .build();
    }

    static SaleOutDto createSaleOutDto(Sale sale, ProductDto productDto) {
        return SaleOutDto.builder()
                .id(sale.getId())
                .userId(sale.getUserId())
                .productId(productDto.getId())
                .name(productDto.getName())
                .price(sale.getPrice())
                .amount(sale.getAmount())
                .multi(sale.getMulti())
                .build();
    }

    static TransferPointsDto createTransferPointsDto(Sale sale, Long fromId, BigDecimal price) {
        return TransferPointsDto.builder()
                .fromId(fromId)
                .toId(sale.getId())
                .amount(price)
                .build();
    }

    static ArchivedSale createArchivedSale(Sale sale, Long clientId, LocalDateTime transactionDate,Integer amount) {
        return ArchivedSale.builder()
                .oldId(sale.getId())
                .ownerId(sale.getUserId())
                .clientId(clientId)
                .price(sale.getPrice())
                .productId(sale.getProductId())
                .transactionDate(Timestamp.valueOf(transactionDate))
                .multi(sale.getMulti())
                .amount(amount)
                .build();
    }

    static ArchivedSaleDto createArchivedSaleDto(ArchivedSale sale, ProductDto productDto) {
        return ArchivedSaleDto.builder()
                .oldId(sale.getOldId())
                .clientId(sale.getClientId())
                .ownerId(sale.getOwnerId())
                .price(sale.getPrice())
                .productId(sale.getProductId())
                .name(productDto.getName())
                .transactionDate(sale.getTransactionDate().toLocalDateTime())
                .multi(sale.getMulti())
                .amount(sale.getAmount())
                .build();
    }

    private static Sale.SaleBuilder createSaleBuilder(SaleInDto saleInDto) {
        Sale.SaleBuilder saleBuilder = Sale.builder()
                .id(saleInDto.getId())
                .productId(saleInDto.getProductId())
                .price(saleInDto.getPrice())
                .multi(saleInDto.isMulti());

        Integer amount = saleInDto.getAmount();
        if (saleInDto.isMulti()) {
            if (amount == null) {
                throw new SaleInDtoArgumentsException("sale is multi but amount is null");
            } else if(amount < 1){
                throw new SaleInDtoArgumentsException("sale is multi but amount is 0");
            }
            saleBuilder.amount(amount);
        } else {
            if (amount == null || amount == 1) {
                saleBuilder.amount(1);
            } else {
                throw new SaleInDtoArgumentsException("sale is single but amount is not single");
            }
        }
        return saleBuilder;
    }
}
