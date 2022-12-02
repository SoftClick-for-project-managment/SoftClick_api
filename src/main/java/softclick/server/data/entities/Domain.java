package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@Table(name = "domain")
public class Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idDomain;
    private String nameDomain;


    public Domain(Long idDomain, String nameDomain) {
        this.idDomain = idDomain;
        this.nameDomain = nameDomain;
    }
}
