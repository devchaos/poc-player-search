package io.devchaos.comm.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Paulo Jesus
 */
@Data
public class PlayerEvent {
    @NotBlank
    private String playerId;
}
