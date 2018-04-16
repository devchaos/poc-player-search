package io.devchaos.player.service.repository;

import io.devchaos.player.service.domain.PlayerAudit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author Paulo Jesus
 */
public interface PlayerAuditRepository extends ReactiveCrudRepository<PlayerAudit, String> {
    Mono<PlayerAudit> findByPlayerId(String playerId);
}
