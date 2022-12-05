package softclick.server.webtier.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * A DTO for the {@link softclick.server.data.entities.Task} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListAndSingleDto implements Serializable {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String Description;
    private Status status;
    private Project project;
    private Employee employee;
    private Priority priority;
    private Set<Expense> expenses;
}