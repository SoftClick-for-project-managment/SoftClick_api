package softclick.server.webtier.services.team;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.*;

import java.util.*;

@Service
@Qualifier("rmiFakeTeamService")
public class FakeTeamService implements  ITeamService{
    private final List<Team> teams = new ArrayList<>();

    String description="c'est une equipe de developpemet";
    Employee employee1=new Employee(null, "Tiger", "Nixon", "Talent Acquisition Specialist", "tigernixon@gmail.com", "+2120065354675");
    Employee employee2=new Employee(null, "Garrett", "Winters", "FullStack Developer", "garrettwinters@gmail.com", "+2120065354675");
    Employee employee3=new Employee(null, "Brielle", "Williamson", "Front-End Developer", "briellewilliamson@gmail.com", "+2120065354675");
    Set<Employee> members = new HashSet<>(Arrays.asList(employee1, employee2,employee3));
    public FakeTeamService(){
        teams.add(new Team("developpement team",description));
    }
    @Override
    public void saveEntity(Team entity) {

    }

    @Override
    public Team findEntityByKey(Long aLong) {
        return teams.get(aLong.intValue());
    }

    @Override
    public List<Team> getAllEntities() {
        return teams;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public Team patch(Long aLong, Map<Object, Object> fields, Class<Team> teamClass) {
        return null;
    }


    @Override
    public void addEmployeeToTeam(Long teamId,String EmployeeLastName) {

    }
    public void UpdateTeam(Long id, Team newTeam) {

    }
}
