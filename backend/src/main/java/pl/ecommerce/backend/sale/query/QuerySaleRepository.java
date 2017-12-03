package pl.ecommerce.backend.sale.query;

import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.sale.dto.SaleDetailDto;
import pl.ecommerce.backend.sale.dto.SaleOutDto;


import java.util.List;
import java.util.Optional;

public interface QuerySaleRepository extends Repository<QuerySale, Long> {
    //@Query("select NEW SaleDetailDto(s.id) from Sale s where s.userId = id")
    QuerySale findById(Long id);
  //  List<SaleOutDto> findSalesByUserId(Long userId);
    List<SaleOutDto>  findAll();
}
