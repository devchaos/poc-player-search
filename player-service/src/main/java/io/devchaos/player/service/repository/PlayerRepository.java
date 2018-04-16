package io.devchaos.player.service.repository;

import io.devchaos.player.service.domain.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Paulo Jesus
 */
public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {
    Flux<Player> findAllByActive(boolean active);

    Mono<Player> findByIdAndActiveIsTrue(String id);
}
