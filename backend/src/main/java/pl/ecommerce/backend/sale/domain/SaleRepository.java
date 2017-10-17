package pl.ecommerce.backend.sale.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SaleRepository extends Repository<Sale, Long> {
    Sale save(Sale entity);
    Optional<Sale> findById(Long id);
    List<Sale> findSalesByUserId(Long userId);
}
