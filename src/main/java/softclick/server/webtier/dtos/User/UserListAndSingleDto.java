package softclick.server.webtier.dtos.User;

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
public class UserListAndSingleDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private Collection<Role> roles;
    private Employee employee;
}