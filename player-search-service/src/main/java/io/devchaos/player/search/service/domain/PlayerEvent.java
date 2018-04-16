package io.devchaos.player.search.service.domain;

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
    private String id;
}
