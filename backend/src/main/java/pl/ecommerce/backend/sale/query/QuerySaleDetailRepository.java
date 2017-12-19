package pl.ecommerce.backend.sale.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.ecommerce.backend.sale.dto.SaleOutDto;

import java.util.List;

public interface QuerySaleDetailRepository extends Repository<QuerySaleDetail, Long> {
    //@Query("select NEW SaleDetailDto(s.id) from Sale s where s.userId = id")
    QuerySaleDetail findById(Long id);
  //  List<SaleOutDto> findSalesByUserId(Long userId);

    @Query("select NEW pl.ecommerce.backend.sale.dto.SaleOutDto(s.id, s.imageId, s.name, s.price, s.buyNow) " +
            "from Sale s")
    List<SaleOutDto>  findAll();
}
