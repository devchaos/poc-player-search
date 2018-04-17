package io.devchaos.comm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Paulo Jesus
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEvent {
    private String playerId;
}
