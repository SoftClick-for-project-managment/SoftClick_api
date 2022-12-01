package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softclick.server.data.entities.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {
}
