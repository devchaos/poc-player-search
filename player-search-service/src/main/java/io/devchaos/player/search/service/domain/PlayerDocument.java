package io.devchaos.player.search.service.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.LocalDateTime;

/**
 * @author Paulo Jesus
 */
@Builder
@Data
@Document(indexName = PlayerDocument.COLLECTION_NAME, type = "player")
@Setting(settingPath = "es-lowercase-analyzer.json")
public class PlayerDocument {
    public static final String COLLECTION_NAME = "players";

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.text),
            otherFields = {
                    @InnerField(suffix = "lowercase", type = FieldType.text, indexAnalyzer = "lowercasean", searchAnalyzer = "standard")
            }
    )
    private String firstName;

    @MultiField(mainField = @Field(type = FieldType.text),
            otherFields = {
                    @InnerField(suffix = "lowercase", type = FieldType.text, indexAnalyzer = "lowercasean", searchAnalyzer = "standard")
            }
    )
    private String lastName;

    @MultiField(mainField = @Field(type = FieldType.text),
            otherFields = {
                    @InnerField(suffix = "lowercase", type = FieldType.text, indexAnalyzer = "lowercasean", searchAnalyzer = "standard")
            }
    )
    private String email;
    private boolean active;
    private LocalDateTime createdDate;
    private boolean verified;

    public static PlayerDocument map(Player player) {
        return PlayerDocument.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .email(player.getEmail())
                .active(player.isActive())
                .createdDate(player.getCreatedDate())
                .verified(player.isVerified())
                .build();
    }
}
