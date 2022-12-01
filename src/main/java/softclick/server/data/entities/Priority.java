package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "priority")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPriority;

    String namePriority;

    private Float  dugreePriority;

    public Priority(Long idPriority, String namePriority, Float dugreePriority) {
        this.idPriority = idPriority;
        this.namePriority = namePriority;
        this.dugreePriority = dugreePriority;
    }
}
