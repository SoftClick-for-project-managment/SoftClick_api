package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@Proxy(lazy = false)
@Table(name = "domain")
public class Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idDomain;
    private String nameDomain;


    public Domain( String nameDomain) {

        this.nameDomain = nameDomain;
    }
}
