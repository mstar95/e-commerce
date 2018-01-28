package pl.ecommerce.backend.sale.domain;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.elasticsearch.core.completion.Completion;

@UtilityClass
class ElasticSearchSaleFactory {
    ElasticSearchSale createElasticSearchSale(Sale sale) {
        return ElasticSearchSale.builder()
                .name(sale.getName())
                .description(sale.getDescription())
                .entityId(sale.getId())
                .buyNow(sale.isBuyNow())
                .created(sale.getCreated())
                .deadline(sale.getDeadline())
                .imageId(sale.getImageId())
                .price(sale.getPrice())
                .completion(createCompletion(sale)).build();
    }

    private Completion createCompletion(Sale sale) {
        String[] split = sale.getName().split(",|\\s+");
        String split2 = sale.getDescription();
        return new Completion((String[])ArrayUtils.add( split,split2));
    }

}
