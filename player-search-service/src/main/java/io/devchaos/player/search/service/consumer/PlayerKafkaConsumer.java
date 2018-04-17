package io.devchaos.player.search.service.consumer;

import io.devchaos.comm.domain.PlayerEvent;
import io.devchaos.player.search.service.config.PlayerKafkaInput;
import io.devchaos.player.search.service.domain.Player;
import io.devchaos.player.search.service.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

@Slf4j
@EnableBinding(PlayerKafkaInput.class)
@AllArgsConstructor
public class PlayerKafkaConsumer {

    private PlayerRepository playerRepository;

    @StreamListener(PlayerKafkaInput.INPUT)
    public void in(@Payload PlayerEvent playerEvent,
                   @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                   @Header(KafkaHeaders.OFFSET) long offset) {

        assertNotEmpty("Player Id Can't be Empty", playerEvent.getPlayerId());

        log.info("Processing topic={} partition={} offset={} key={} PlayerEvent= {}", topic, partition, offset, key, playerEvent);

        Optional<Player> player = playerRepository.findById(playerEvent.getPlayerId());

        log.info("Player = {} was indexed", player);
    }

}