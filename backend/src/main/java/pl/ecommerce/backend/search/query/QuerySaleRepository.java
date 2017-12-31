package pl.ecommerce.backend.search.query;

import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.search.dto.SaleNameDto;

import java.util.List;

interface QuerySaleRepository extends Repository<QuerySale, Long> {

    List<SaleNameDto>  findByNameStartsWith(String name);

    List<QuerySale>  findByName(String name);
}
