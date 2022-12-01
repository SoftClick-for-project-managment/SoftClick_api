package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority , Long> {
}
