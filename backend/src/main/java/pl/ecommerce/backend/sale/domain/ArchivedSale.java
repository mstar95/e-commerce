package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
class ArchivedSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private byte[] image;
    private Long userId;
    private BigDecimal price;
    private LocalDateTime created;
    private boolean buyNow;
    private Long ownerId;
    private Long clientId;
    private Timestamp transactionDate;
}
