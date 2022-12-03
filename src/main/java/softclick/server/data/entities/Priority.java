package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@Table(name = "priority")
public class Priority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPriority;

    String namePriority;

    private Float  dugreePriority;

    public Priority( String namePriority, Float dugreePriority) {

        this.namePriority = namePriority;
        this.dugreePriority = dugreePriority;
    }
}
