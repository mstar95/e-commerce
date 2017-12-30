package pl.ecommerce.backend.search.query;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "sale",type = "elasticsearchsale" )
public class ElasticSearchQuerySale {
    @Id
    private String id;
    private Long entityId;
    private String name;
    private String description;
}
