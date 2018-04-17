package io.devchaos.player.search.service.repository;

import io.devchaos.player.search.service.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Paulo Jesus
 */
public interface PlayerRepository extends MongoRepository<Player, String> {

}
