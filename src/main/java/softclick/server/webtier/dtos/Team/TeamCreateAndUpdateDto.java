package softclick.server.webtier.dtos.Team;

import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Employee;

import java.io.Serializable;
import java.util.Set;
@Data
@NoArgsConstructor
public class TeamCreateAndUpdateDto implements Serializable {
    private Long idTeam;
    private String team_Name;
    private String description;
    private Set<Employee> members;
}
