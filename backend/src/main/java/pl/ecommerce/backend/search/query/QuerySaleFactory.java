package pl.ecommerce.backend.search.query;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;

class QuerySaleFactory {
    QueryBuilder createQueryBuilder(String name) {

        SuggestionBuilder completionSuggestionFuzzyBuilder = SuggestBuilders
                .completionSuggestion("name").prefix("m", Fuzziness.AUTO);

        return null;
    }
}
