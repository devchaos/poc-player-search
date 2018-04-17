package io.devchaos.player.search.service.service;

import io.devchaos.player.search.service.domain.player.PlayerDocument;
import io.devchaos.player.search.service.repository.PlayerDocumentRepository;
import io.devchaos.player.search.service.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Paulo Jesus
 */
@Service
@Slf4j
@AllArgsConstructor
public class PlayerIndexingServiceImpl implements PlayerIndexingService {

    private PlayerRepository playerRepository;
    private PlayerDocumentRepository playerDocumentRepository;

    @Override
    public void index(String playerId) {
        playerRepository.findById(playerId).ifPresent(it -> {
            PlayerDocument document = playerDocumentRepository.save(PlayerDocument.map(it));
            log.info("PlayerDocument = {} was successfully indexed", document);
        });
    }
}
