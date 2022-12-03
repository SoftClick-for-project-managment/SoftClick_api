package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Proxy(lazy=false)
@Data
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClient")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProject")
    private Project project;

    public Invoice(String date, String total) {
        this.date = date;
        this.total = total;
    }
    public Invoice(String date, String total, Client client, Project project) {
        this.date = date;
        this.total = total;
        this.project=project;
        this.client=client;
    }
}
