package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select t from Team t where t.name = ?1")
    List<Team> find_by_nom(String nom);
    @Query("select t from Team t where t.membernumber = ?1")
    List<Team> find_by_membernumber(int number_of_members);
    @Override
    void deleteById(Long aLong);
}