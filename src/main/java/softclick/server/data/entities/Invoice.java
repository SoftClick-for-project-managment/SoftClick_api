package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private double total;
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project project;

    public Invoice(Date date, Double total) {
        this.date = date;
        this.total = total;
    }
    public Invoice(Date date, Double total,Client client,Project project) {
        this.date = date;
        this.total = total;
        this.project=project;
    }
}
