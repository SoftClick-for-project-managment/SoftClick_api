package softclick.server.webtier.dtos.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link softclick.server.data.entities.User} entity
 */
@Data @NoArgsConstructor
public class UserCreateAndUpdateDto implements Serializable {
    private String username;
    private String password;
    @JsonProperty(value = "active")
    private boolean isActive;
}