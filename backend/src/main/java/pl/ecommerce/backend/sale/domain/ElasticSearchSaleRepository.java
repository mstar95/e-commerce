package pl.ecommerce.backend.sale.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface ElasticSearchSaleRepository extends ElasticsearchRepository<ElasticSearchSale, String> {

    void deleteByEntityId(Long id);

}
