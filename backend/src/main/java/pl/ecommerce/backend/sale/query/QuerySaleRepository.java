package pl.ecommerce.backend.sale.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.sale.dto.SaleInfoDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;


import java.util.List;
import java.util.Optional;

public interface QuerySaleRepository extends Repository<QuerySale, Long> {
    //@Query("select NEW SaleInfoDto(s.id) from Sale s where s.userId = id")
    Optional<SaleInfoDto> findById(Long id);
    List<SaleOutDto> findSalesByUserId(Long userId);
    List<SaleOutDto>  findAll();
}
