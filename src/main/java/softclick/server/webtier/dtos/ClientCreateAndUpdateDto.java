package softclick.server.webtier.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
@Data
@NoArgsConstructor
public class ClientCreateAndUpdateDto implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String nomEntreprise;
    private String ville;
    private String pays;
}
