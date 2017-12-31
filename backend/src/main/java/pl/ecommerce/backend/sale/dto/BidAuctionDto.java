package pl.ecommerce.backend.sale.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
@NotNull
public class BidAuctionDto {
    Long auctionId;
    BigDecimal amount;
}
