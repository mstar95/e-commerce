package pl.ecommerce.backend.sale.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.completion.Completion;

@Data
@Builder
@Document(indexName = "sale")
class ElasticSearchSale {
    @Id
    private String id;
    private Long entityId;
    private String name;
    private String description;
    @CompletionField
    private Completion completion;
}
