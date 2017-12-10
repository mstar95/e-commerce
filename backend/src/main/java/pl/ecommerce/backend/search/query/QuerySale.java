package pl.ecommerce.backend.search.query;

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
public class QuerySale {
    @Id
    private Long id;
    private String name;
    private Long imageId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadline;
    private boolean buyNow;
}
