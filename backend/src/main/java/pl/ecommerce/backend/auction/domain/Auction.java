package pl.ecommerce.backend.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@Entity
class Auction {
    @Id
    private Long id;
    private Long userId;
    private Long productId;
}
