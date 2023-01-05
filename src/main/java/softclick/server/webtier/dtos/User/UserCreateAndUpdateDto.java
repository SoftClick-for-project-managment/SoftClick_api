package softclick.server.webtier.dtos.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Role;

import java.io.Serializable;
import java.util.Collection;

/**
 * A DTO for the {@link softclick.server.data.entities.User} entity
 */
@Data @NoArgsConstructor
public class UserCreateAndUpdateDto implements Serializable {
    private String username;
    private String password;
    @JsonProperty(value = "active")
    private Boolean isActive;
    private Employee employee;
    private Collection<Role> roles;
}