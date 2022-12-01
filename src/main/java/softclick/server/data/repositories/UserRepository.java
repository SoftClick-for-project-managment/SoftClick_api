package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}