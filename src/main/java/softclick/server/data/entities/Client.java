package softclick.server.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String email;
    private String phone;
    @Column(nullable = false)
    private String nomEntreprise;
    private String ville;
    @Column(nullable = false)
    private String pays;

    public Client(String nom, String prenom, String email, String phone, String nomEntreprise, String ville, String pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.nomEntreprise = nomEntreprise;
        this.ville = ville;
        this.pays = pays;
    }
}
