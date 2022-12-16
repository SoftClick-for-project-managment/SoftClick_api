package softclick.server.webtier.dtos.Client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class ClientListAndSingleDto implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String nomEntreprise;
    private String ville;
    private String pays;
}
