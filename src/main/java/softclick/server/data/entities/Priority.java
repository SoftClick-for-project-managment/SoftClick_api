package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@NoArgsConstructor
@Proxy(lazy=false)
@Table(name = "priority")
public class Priority implements Serializable, Comparable<Priority> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPriority;

    String namePriority;

    private Double  dugreePriority;

    public Priority( String namePriority, Double dugreePriority) {

        this.namePriority = namePriority;
        this.dugreePriority = dugreePriority;
    }

    @Override
    public int compareTo(Priority priority) {
        return Comparator.comparing(Priority::getNamePriority)
                .thenComparing(Priority::getDugreePriority)
                .compare(this, priority);
    }

    public Long getIdPriority() {
        return idPriority;
    }

    public void setIdPriority(Long idPriority) {
        this.idPriority = idPriority;
    }

    public String getNamePriority() {
        return namePriority;
    }

    public void setNamePriority(String namePriority) {
        this.namePriority = namePriority;
    }

    public Double getDugreePriority() {
        return dugreePriority;
    }

    public void setDugreePriority(Double dugreePriority) {
        this.dugreePriority = dugreePriority;
    }
}
