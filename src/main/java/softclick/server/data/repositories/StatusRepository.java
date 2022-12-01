package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status , Long> {
}
