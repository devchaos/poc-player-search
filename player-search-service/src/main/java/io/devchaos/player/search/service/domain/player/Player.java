package io.devchaos.player.search.service.domain.player;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Paulo Jesus
 */
@Data
@Document
public class Player {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private LocalDateTime createdDate;
    private boolean verified;
    private boolean duplicate;
    private Set<String> duplicates;
}
