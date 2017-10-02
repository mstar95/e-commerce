package pl.ecommerce.backend.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuctionOutDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String name;
}
