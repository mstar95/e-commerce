package pl.ecommerce.backend.search.query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticSearchQuerySaleRepository  extends ElasticsearchRepository<ElasticSearchQuerySale, String> {
    List<ElasticSearchQuerySale> findByNameStartsWithOrDescriptionStartsWith(String a,String b);
}
