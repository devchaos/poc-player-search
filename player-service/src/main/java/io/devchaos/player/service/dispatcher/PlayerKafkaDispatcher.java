package io.devchaos.player.service.dispatcher;

import io.devchaos.player.service.config.PlayerKafkaOutput;
import io.devchaos.player.service.domain.Player;
import io.devchaos.player.service.domain.PlayerEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/**
 * @author Paulo Jesus
 */

@Component
@EnableBinding(PlayerKafkaOutput.class)
public class PlayerKafkaDispatcher {

    private PlayerKafkaOutput playerKafkaOutput;

    public PlayerKafkaDispatcher(PlayerKafkaOutput playerKafkaOutput) {
        this.playerKafkaOutput = playerKafkaOutput;
    }

    public Mono<Player> dispatch(Mono<Player> player) {
        return player.flatMap(it -> {
            playerKafkaOutput.out().send(
                    MessageBuilder.withPayload(
                            PlayerEvent.builder()
                                    .id(it.getId())
                                    .build()
                    ).setHeader(KafkaHeaders.MESSAGE_KEY, it.getId()).build());
            return player;
        });
    }
}
