package pl.ecommerce.backend.search.query;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Document(indexName = "sale",type = "elasticsearchsale" )
class ElasticSearchQuerySale {
    @Id
    private String id;
    private Long entityId;
    private String description;
    private String name;
    private Long imageId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadline;
    private boolean buyNow;
}
