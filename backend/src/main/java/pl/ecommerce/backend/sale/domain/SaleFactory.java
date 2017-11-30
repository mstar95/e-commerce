package pl.ecommerce.backend.sale.domain;

import lombok.experimental.UtilityClass;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.sale.dto.ArchivedSaleDto;
import pl.ecommerce.backend.sale.dto.CreateSaleDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@UtilityClass
class SaleFactory {

    static Sale createSale(CreateSaleDto createSaleDto) {
        return Sale.builder()
                .name(createSaleDto.getName())
                .description(createSaleDto.getDescription())
                .image(createSaleDto.getImage())
                .price(createSaleDto.getPrice())
                .buyNow(createSaleDto.isBuyNow())
                .deadline(Timestamp.valueOf(createSaleDto.getDeadline()))
                .build();
    }


    static TransferPointsDto createTransferPointsDto(Sale sale, Long fromId) {
        return TransferPointsDto.builder()
                .fromId(fromId)
                .toId(sale.getId())
                .amount(sale.getPrice())
                .build();
    }

    static ArchivedSaleDto createArchivedSaleDto(ArchivedSale archivedSale) {
        return ArchivedSaleDto.builder()
                .ownerId(archivedSale.getOwnerId())
                .clientId(archivedSale.getClientId())
                .price(archivedSale.getPrice())
                .name(archivedSale.getName())
                .image(archivedSale.getImage())
                .transactionDate(archivedSale.getTransactionDate()).build();
    }

    static ArchivedSale createArchivedSale(Sale sale, Long currentUserId, LocalDateTime currentDate) {
        return ArchivedSale.builder()
                .name(sale.getName())
                .image(sale.getImage())
                .price(sale.getPrice())
                .ownerId(sale.getUserId())
                .clientId(currentUserId)
                .buyNow(sale.isBuyNow())
                .transactionDate(Timestamp.valueOf(currentDate)).build();

    }
}
