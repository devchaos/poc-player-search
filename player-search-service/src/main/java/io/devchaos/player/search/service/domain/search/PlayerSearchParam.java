package io.devchaos.player.search.service.domain.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Paulo Jesus
 */
public class PlayerSearchParam {
    private PlayerSearchParam() {
    }

    private static final Map<String, Function<Map.Entry<String, List<String>>, QueryBuilder>> authorized;

    static {
        Map<String, Function<Map.Entry<String, List<String>>, QueryBuilder>> map = new HashMap<>();
        map.put("firstName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0)));
        map.put("lastName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0)));
        map.put("email", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0)));
        map.put("verified", e -> QueryBuilders.matchQuery(e.getKey(), Boolean.valueOf(e.getValue().get(0))));

        authorized = Collections.unmodifiableMap(map);
    }

    public static QueryBuilder build(Map.Entry<String, List<String>> requestParamEntry) throws InvalidSearchParamException {
        validateParameter(requestParamEntry.getKey());
        return authorized.get(requestParamEntry.getKey()).apply(requestParamEntry);
    }

    public static void validateParameter(String requestParam) throws InvalidSearchParamException {
        if (!authorized.keySet().contains(requestParam)) {
            throw new InvalidSearchParamException(String.format("Invalid parameter '%s'. Valid params are: %s", requestParam, authorized.keySet()));
        }
    }
}
