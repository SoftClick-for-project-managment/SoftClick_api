package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
