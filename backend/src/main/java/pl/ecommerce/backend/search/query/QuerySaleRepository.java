package pl.ecommerce.backend.search.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.search.dto.SaleNameDto;

import java.util.List;

interface QuerySaleRepository extends Repository<QuerySale, Long> {

    Page<SaleNameDto> findByNameStartsWith(String name, Pageable pageable);

    Page<QuerySale>  findByName(String name, Pageable pageable);
}
