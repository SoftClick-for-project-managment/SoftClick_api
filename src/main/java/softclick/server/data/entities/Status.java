package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@NoArgsConstructor
@Proxy(lazy=false)
@Data
public class Status implements Serializable, Comparable<Status> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    private String nameStatus;

    public Status( String nameStatus) {

        this.nameStatus = nameStatus;
    }

    @Override
    public int compareTo(Status status) {
        return Comparator.comparing(Status::getNameStatus).compare(this, status);
    }
}
