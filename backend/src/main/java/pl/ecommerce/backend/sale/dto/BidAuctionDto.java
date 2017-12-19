package pl.ecommerce.backend.sale.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class BidAuctionDto {
    Long auctionId;
    BigDecimal amount;
}
