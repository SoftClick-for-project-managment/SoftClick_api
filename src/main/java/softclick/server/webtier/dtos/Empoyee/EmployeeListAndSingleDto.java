package softclick.server.webtier.dtos.Empoyee;

import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Skill;
import softclick.server.data.entities.User;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor

public class EmployeeListAndSingleDto implements Serializable {

    private Long id;
    private String employeeImage;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeFunction;
    private String employeeEmail;
    private String employeePhone;
    private User user;
    private Set<Skill> skills;

}
