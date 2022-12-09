package softclick.server.webtier.dtos.Empoyee;

import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Skill;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeCreateAndUpdateDto implements Serializable {
    private String employeeImage;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeFunction;
    private String employeeEmail;
    private String employeePhone;
    private Set<Skill> skills;
}
