package softclick.server.webtier.services.team;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Skill;
import softclick.server.data.entities.Team;
import softclick.server.data.repositories.EmployeeRepository;
import softclick.server.data.repositories.SkillRepository;
import softclick.server.data.repositories.TeamRepository;
import softclick.server.webtier.services.BaseService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
@Qualifier("rmiTeamService")
public class TeamService extends BaseService<Team, Long> implements ITeamService{
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    @Autowired
    protected TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        super(teamRepository);
        this.employeeRepository=employeeRepository;
        this.teamRepository=teamRepository;
    }
    public void UpdateTeam(Long id, Team newTeam){
        log.info("Updating team with id: {}", id.toString());
        Team team = teamRepository.getReferenceById(id);
        if (team == null)
            throw new EntityNotFoundException();
        team.setTeam_Name(newTeam.getTeam_Name());
        team.setDescription(newTeam.getDescription());
        if(newTeam.getMembers() != null){
            team.setMembers(newTeam.getMembers());
        }

        teamRepository.save(team);
    }

    @Override
    public List<Team> serachTeam(String teamName, Employee member) {
        List<Team> filteredTeams = teamRepository.searchTeam(teamName ,  member);
        return  filteredTeams;
    }

    public void addEmployeeToTeam(Long teamId,String EmployeeLastName) {
        log.info("Adding employee {} to team with id {}", teamId,EmployeeLastName);
        Employee employee = employeeRepository.findByEmployeeLastName(EmployeeLastName);
        Team team = teamRepository.findById(teamId).get();
        if(team.getMembers().contains(employee))
            throw new RuntimeException("Team already has the given employee");
        Set<Employee> employees = team.getMembers();
        employees.add(employee);
        team.setMembers(employees);
        teamRepository.save(team);
    }

    public void UpdateProjectInTeams(Long id ,List<Team> teams ){
        Project project = new Project();
        project.setIdProject(id);
        teams.forEach(team ->{

          //  team.setProject(project);

        });
    }
}
