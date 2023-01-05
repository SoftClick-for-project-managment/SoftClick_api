package softclick.server.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softclick.server.data.entities.*;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByName(String name);
    @Query("SELECT t FROM Task t WHERE t.project=:project AND t.name LIKE :kw")
    List<Task> findByProjectAndKeyword(@Param("project") Project project, @Param("kw") String kw);
    @Query("SELECT t FROM Task t WHERE t.project=:project")
    Page<Task> getByProjectPage(@Param("project") Project project, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.employee=:employee")
    Page<Task> getByEmployeePage(@Param("employee") Employee employee, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project=:project AND t.employee=:employee")
    Page<Task> getByProjectAndEmployeePage(@Param("project") Project project, @Param("employee") Employee employee, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project=:project AND t.status=:status")
    Page<Task> getByProjectAndStatusPage(@Param("project") Project project, @Param("status") Status status, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project=:project AND t.priority=:priority")
    Page<Task> getByProjectAndPriorityPage(@Param("project") Project project, @Param("priority") Priority priority, Pageable pageable);
}
