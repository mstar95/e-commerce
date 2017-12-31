package pl.ecommerce.backend.sale.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Document(indexName = "sale")
class ElasticSearchSale {
    @Id
    private String id;
    private Long entityId;
    private String name;
    private String description;
    private Long imageId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp deadline;
    private boolean buyNow;
    @CompletionField
    private Completion completion;
}
