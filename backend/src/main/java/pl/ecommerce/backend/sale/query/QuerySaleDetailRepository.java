package pl.ecommerce.backend.sale.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

public interface QuerySaleDetailRepository extends Repository<QuerySaleDetail, Long> {

    @Query("select NEW pl.ecommerce.backend.sale.dto.SaleOutDto" +
            "(s.id, s.imageId, s.name, s.price, s.buyNow) " +
            "from Sale s")
    Page<SaleOutDto> findAll(Pageable pageable);
}


