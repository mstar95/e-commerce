package pl.ecommerce.backend.sale.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface SaleRepository extends CrudRepository<Sale, Long> {
    List<Sale> findSalesByUserId(long userId);
}
