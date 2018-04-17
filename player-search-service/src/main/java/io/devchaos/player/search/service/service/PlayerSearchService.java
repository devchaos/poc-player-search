package io.devchaos.player.search.service.service;

import io.devchaos.player.search.service.domain.player.PlayerDocument;
import io.devchaos.player.search.service.domain.search.InvalidSearchParamException;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author Paulo Jesus
 */
public interface PlayerSearchService {
    List<PlayerDocument> search(MultiValueMap<String, String> queryParams) throws InvalidSearchParamException;
}
