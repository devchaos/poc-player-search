package io.devchaos.player.search.service.service;

import io.devchaos.player.search.service.domain.player.PlayerDocument;
import io.devchaos.player.search.service.domain.search.InvalidSearchParamException;
import io.devchaos.player.search.service.domain.search.PlayerSearchParam;
import io.devchaos.player.search.service.repository.PlayerDocumentRepository;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Paulo Jesus
 */
@Service
@AllArgsConstructor
public class PlayerSearchServiceImpl implements PlayerSearchService {

    private PlayerDocumentRepository playerDocumentRepository;

    @Override
    public List<PlayerDocument> search(final MultiValueMap<String, String> queryParams) throws InvalidSearchParamException {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (Map.Entry<String, List<String>> param : queryParams.entrySet()) {
            boolQueryBuilder.must(PlayerSearchParam.build(param));
        }

        return StreamSupport.stream(playerDocumentRepository.search(boolQueryBuilder).spliterator(), false)
                .collect(Collectors.toList());
    }
}
