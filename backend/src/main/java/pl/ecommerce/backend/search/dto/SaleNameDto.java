package pl.ecommerce.backend.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SaleNameDto {
    String name;
}
