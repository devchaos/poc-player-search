package io.devchaos.player.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;

/**
 * @author Paulo Jesus
 */
@Data
@Builder
@AllArgsConstructor
@Document
public class PlayerAudit {
    @Id
    private String id;
    @Indexed(unique = true)
    private String playerId;
    private Long version;

    @Reference
    private LinkedHashMap<Long, Player> history;
}
