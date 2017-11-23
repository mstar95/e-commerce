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
class Sale {
    @Id
    private Long id;
    private String name;
    private byte[] image;
    private Long userId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadLine;
    private boolean auction;
}
