package softclick.server.webtier.dtos.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Priority;
import softclick.server.data.entities.Project;
import softclick.server.data.entities.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link softclick.server.data.entities.Task} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateAndUpdateDto implements Serializable {
    private String name;
    private String startDate;
    private String endDate;
    private String description;
    private Status status;
    private Project project;
    private Employee employee;
    private Priority priority;
}