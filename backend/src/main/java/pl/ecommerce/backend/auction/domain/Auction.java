package pl.ecommerce.backend.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@Entity
class Auction {
    private Long id;
    private Long userId;
    private Long productId;
}
