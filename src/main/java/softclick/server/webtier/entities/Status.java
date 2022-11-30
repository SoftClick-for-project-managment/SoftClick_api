package softclick.server.webtier.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStatus;

    private String nameStatus;

    public Status(Long idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }
}
