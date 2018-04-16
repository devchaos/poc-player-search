package io.devchaos.player.search.service.listener;

import io.devchaos.player.search.service.config.PlayerKafkaInput;
import io.devchaos.player.search.service.domain.PlayerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@EnableBinding(PlayerKafkaInput.class)
public class PlayerKafkaConsumer {

    @StreamListener(PlayerKafkaInput.INPUT)
    public void in(@Payload PlayerEvent in,
                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                   @Header(KafkaHeaders.OFFSET) long offset) {
        log.info(in + " from partition " + partition + " at offset " + offset);
    }

}