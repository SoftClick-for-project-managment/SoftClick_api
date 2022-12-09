package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "domain")
public class Domain implements Serializable, Comparable<Domain> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idDomain;
    private String nameDomain;


    public Domain( String nameDomain) {

        this.nameDomain = nameDomain;
    }

    @Override
    public int compareTo(Domain domain) {
        return Comparator.comparing(Domain::getNameDomain).compare(this, domain);
    }

    public Long getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(Long idDomain) {
        this.idDomain = idDomain;
    }

    public String getNameDomain() {
        return nameDomain;
    }

    public void setNameDomain(String nameDomain) {
        this.nameDomain = nameDomain;
    }
}
