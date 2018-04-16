package io.devchaos.player.service.service;

import io.devchaos.player.service.domain.Player;
import io.devchaos.player.service.domain.PlayerAudit;
import io.devchaos.player.service.repository.PlayerAuditRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

/**
 * @author Paulo Jesus
 */
@Service
public class PlayerAuditServiceImpl implements PlayerAuditService {
    private PlayerAuditRepository playerAuditRepository;

    public PlayerAuditServiceImpl(PlayerAuditRepository playerAuditRepository) {
        this.playerAuditRepository = playerAuditRepository;
    }

    @Override
    public Mono<Player> audit(Mono<Player> playerMono) {
        return playerMono.flatMap(player -> playerAuditRepository.findByPlayerId(player.getId())
                .flatMap(it -> update(it, player))
                .switchIfEmpty(create(player))
        ).flatMap(audit -> playerMono);
    }

    private Mono<PlayerAudit> update(PlayerAudit audit, Player player) {
        Long newVersion = audit.getVersion() + 1l;
        audit.setVersion(newVersion);
        audit.getHistory().put(newVersion, player);
        return playerAuditRepository.save(audit);
    }

    private Mono<PlayerAudit> create(Player player) {
        LinkedHashMap<Long, Player> history = new LinkedHashMap<>();
        history.put(1l, player);

        PlayerAudit audit = PlayerAudit.builder()
                .version(1l)
                .playerId(player.getId())
                .history(history)
                .build();

        return playerAuditRepository.save(audit);
    }
}
