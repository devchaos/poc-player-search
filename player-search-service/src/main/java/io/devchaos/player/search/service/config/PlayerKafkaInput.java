package io.devchaos.player.search.service.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PlayerKafkaInput {
    String INPUT = "player-in";

    @Input(INPUT)
    SubscribableChannel in();
}