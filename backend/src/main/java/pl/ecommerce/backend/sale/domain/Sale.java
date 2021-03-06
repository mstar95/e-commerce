package pl.ecommerce.backend.sale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long imageId;
    private Long userId;
    private Long winnerId;
    private String name;
    @Column(columnDefinition="TEXT")
    private String description;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadline;
    private boolean buyNow;
}
