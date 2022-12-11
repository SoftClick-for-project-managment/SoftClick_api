package softclick.server.webtier.services.team;

import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Team;
import softclick.server.webtier.services.IBaseService;

public interface ITeamService extends IBaseService<Team, Long> {
    void addEmployeeToTeam(Long teamId,String EmployeeLastName);
    void UpdateTeam(Long id, Team newTeam);
}
