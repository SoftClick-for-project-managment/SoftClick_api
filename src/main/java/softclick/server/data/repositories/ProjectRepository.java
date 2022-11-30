package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softclick.server.webtier.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
