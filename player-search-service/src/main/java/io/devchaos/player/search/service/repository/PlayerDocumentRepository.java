package io.devchaos.player.search.service.repository;

import io.devchaos.player.search.service.domain.player.PlayerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Paulo Jesus
 */
public interface PlayerDocumentRepository extends ElasticsearchRepository<PlayerDocument, String> {
}
