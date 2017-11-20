package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
class ArchivedSale {
    @Id
    private Long id;
    private String name;
    private byte[] image;
    private Long userId;
    private BigDecimal price;
    private LocalDateTime created;
    private boolean auction;
    private Long ownerId;
    private Long clientId;
    private Timestamp transactionDate;
}
