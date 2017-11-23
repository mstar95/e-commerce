package pl.ecommerce.backend.sale.query;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "sale")
class QuerySale {
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
