package io.devchaos.player.service.service;

import io.devchaos.player.service.domain.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Paulo Jesus
 */

public interface PlayerService {
    Flux<Player> getAll(Optional<Boolean> active);

    Mono<Player> create(Player player);

    Mono<Player> getPlayer(String id);

    Mono<Player> update(String id, Player player);

    Mono<Player> disable(String id);
}
