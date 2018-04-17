package io.devchaos.player.service.service;

import io.devchaos.player.service.dispatcher.PlayerKafkaDispatcher;
import io.devchaos.player.service.domain.Player;
import io.devchaos.player.service.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Paulo Jesus
 */
@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private PlayerAuditService playerAuditService;
    private PlayerKafkaDispatcher playerKafkaDispatcher;

    @Override
    public Flux<Player> getAll(Optional<Boolean> active) {
        if (active.isPresent()) {
            return playerRepository.findAllByActive(active.get());
        } else {
            return playerRepository.findAll();
        }
    }

    @Override
    public Mono<Player> create(final Player player) {
        player.setCreatedDate(LocalDateTime.now());
        return playerRepository.save(player)
                .flatMap(it -> playerKafkaDispatcher.dispatch(Mono.just(it)))
                .log();
    }

    @Override
    public Mono<Player> getPlayer(String id) {
        return playerRepository.findByIdAndActiveIsTrue(id);
    }

    @Override
    public Mono<Player> update(String id, final Player player) {
        return getPlayer(id)
                .flatMap(it -> playerAuditService.audit(Mono.just(it)))
                .flatMap(it -> {
                    it.setFirstName(player.getFirstName());
                    it.setLastName(player.getLastName());
                    return playerRepository.save(it);
                })
                .flatMap(it -> playerKafkaDispatcher.dispatch(Mono.just(it)))
                .log();
    }

    @Override
    public Mono<Player> disable(String id) {
        return getPlayer(id).flatMap(it -> {
            it.setActive(false);
            return playerRepository.save(it);
        }).log();
    }

}
