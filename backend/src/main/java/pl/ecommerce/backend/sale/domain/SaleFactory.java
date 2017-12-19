package pl.ecommerce.backend.sale.domain;

import lombok.experimental.UtilityClass;
import pl.ecommerce.backend.message.dto.CreateFinalizeSaleMessageDto;
import pl.ecommerce.backend.payment.dtos.LockPointsDto;
import pl.ecommerce.backend.payment.dtos.TransferPointsDto;
import pl.ecommerce.backend.sale.dto.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@UtilityClass
class SaleFactory {

    static Sale createSale(CreateSaleDto createSaleDto) {
        return Sale.builder()
                .name(createSaleDto.getName())
                .description(createSaleDto.getDescription())
                .imageId(createSaleDto.getImageId())
                .price(createSaleDto.getPrice())
                .buyNow(createSaleDto.isBuyNow())
                .deadline(Timestamp.valueOf(createSaleDto.getDeadline()))
                .build();
    }

    static TransferPointsDto createTransferPointsDto(Sale sale, Long fromId) {
        return TransferPointsDto.builder()
                .fromId(fromId)
                .toId(sale.getUserId())
                .amount(sale.getPrice())
                .build();
    }

    static LockPointsDto createLockPointsDto(BigDecimal amount, Long fromId) {
        return LockPointsDto.builder()
                .userId(fromId)
                .amount(amount)
                .build();
    }

    static ArchivedSaleDto createArchivedSaleDto(ArchivedSale archivedSale) {
        return ArchivedSaleDto.builder()
                .ownerId(archivedSale.getOwnerId())
                .clientId(archivedSale.getClientId())
                .price(archivedSale.getPrice())
                .name(archivedSale.getName())
                .imageId(archivedSale.getImageId())
                .transactionDate(archivedSale.getTransactionDate()).build();
    }

    static ArchivedSale createArchivedSale(Sale sale, Long currentUserId, LocalDateTime currentDate) {
        return ArchivedSale.builder()
                .name(sale.getName())
                .imageId(sale.getImageId())
                .price(sale.getPrice())
                .ownerId(sale.getUserId())
                .clientId(currentUserId)
                .buyNow(sale.isBuyNow())
                .transactionDate(Timestamp.valueOf(currentDate)).build();

    }

    CreateFinalizeSaleMessageDto createFinalizeSaleMessage(Sale sale, Long buyerId) {
        return CreateFinalizeSaleMessageDto.builder()
                .amount(sale.getPrice())
                .sellerId(sale.getUserId())
                .buyerId(buyerId)
                .productName(sale.getName()).build();
    }

    SaleDetailDto createSaleDetailDto(Sale sale, boolean isOwner, boolean isWinner) {
        return SaleDetailDto.builder()
                .price(sale.getPrice())
                .name(sale.getName())
                .id(sale.getId())
                .buyNow(sale.isBuyNow())
                .imageId(sale.getImageId())
                .buyNow(sale.isBuyNow())
                .created(sale.getCreated().toLocalDateTime())
                .deadline(sale.getDeadline().toLocalDateTime())
                .description(sale.getDescription())
                .isOwner(isOwner)
                .isWinner(isWinner).build();
    }

}
