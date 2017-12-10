package pl.ecommerce.backend.sale.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "sale")
@AllArgsConstructor
@NoArgsConstructor
public class QuerySaleDetail {
    @Id
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private Long imageId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadline;
    private boolean buyNow;
}
