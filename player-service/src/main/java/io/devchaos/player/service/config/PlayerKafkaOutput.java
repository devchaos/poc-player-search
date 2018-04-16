package io.devchaos.player.service.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerKafkaOutput {
    String OUTPUT = "player-out";

    @Output(OUTPUT)
    MessageChannel out();
}