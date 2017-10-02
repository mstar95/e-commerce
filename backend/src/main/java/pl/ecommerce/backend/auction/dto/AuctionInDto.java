package pl.ecommerce.backend.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuctionInDto {
    private Long id;
    private Long productId;
}
