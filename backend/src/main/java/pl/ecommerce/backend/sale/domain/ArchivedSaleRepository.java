package pl.ecommerce.backend.sale.domain;

import org.springframework.data.repository.Repository;

interface ArchivedSaleRepository  extends Repository<ArchivedSale, Long> {
    ArchivedSale save(ArchivedSale entity);
}
