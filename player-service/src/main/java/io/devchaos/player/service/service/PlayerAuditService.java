package io.devchaos.player.service.service;

import io.devchaos.player.service.domain.Player;
import reactor.core.publisher.Mono;

/**
 * @author Paulo Jesus
 */
public interface PlayerAuditService {
    Mono<Player> audit(Mono<Player> player);
}
