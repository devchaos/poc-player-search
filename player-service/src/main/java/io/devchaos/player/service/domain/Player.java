package io.devchaos.player.service.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Paulo Jesus
 */
@Data
@Builder
@AllArgsConstructor
@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Player {
    @Id
    private String id;
    @NotBlank(groups = {ValidateOnCreate.class, ValidateOnUpdate.class})
    private String firstName;
    private String lastName;
    @Email(groups = {ValidateOnCreate.class})
    @NotBlank(groups = {ValidateOnCreate.class})
    @Indexed(unique = true)
    private String email;
    @Indexed
    private boolean active;
    private LocalDateTime createdDate;
    private boolean verified;
    private boolean duplicate;
    private Set<String> duplicates;

    @SuppressWarnings("unused")
    public Player() {//jackson
        this.active = true;
    }

    public interface ValidateOnCreate {
        // validation group marker interface
    }

    public interface ValidateOnUpdate {
        // validation group marker interface
    }
}
