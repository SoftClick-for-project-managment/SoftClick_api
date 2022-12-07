package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softclick.server.data.entities.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select t from Team t where t.team_Name = ?1")
    List<Team> find_by_nom(String nom);
    @Query("select t from Team t where t.members.size = ?1")
    List<Team> find_by_membernumber(int number_of_members);
}