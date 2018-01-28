package pl.ecommerce.backend.search.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface ElasticSearchQuerySaleRepository extends ElasticsearchRepository<ElasticSearchQuerySale, String> {
    Page<ElasticSearchQuerySale> findByNameStartsWithOrDescriptionStartsWith(String a, String b, Pageable pageable);
}
