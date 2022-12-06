package softclick.server.webtier.dtos.Empoyee;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmployeeCreateAndUpdateDto implements Serializable {
    private String employeeImage;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeFunction;
    private String employeeEmail;
    private String employeePhone;
}
