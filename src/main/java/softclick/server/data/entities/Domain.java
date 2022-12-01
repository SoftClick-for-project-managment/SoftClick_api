package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "domain")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long idDomain;
    private String nameDomain;


    public Domain(Long idDomain, String nameDomain) {
        this.idDomain = idDomain;
        this.nameDomain = nameDomain;
    }
}
