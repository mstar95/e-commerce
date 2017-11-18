package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@Entity
class ArchivedSale {
    @Id
    private Long id;
    private Long oldId;
    private Long ownerId;
    private Long clientId;
    private Long productId;
    private BigDecimal price;
    private Timestamp transactionDate;
    private Boolean multi;
    private Integer amount;
}
