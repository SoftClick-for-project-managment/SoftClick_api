package softclick.server.webtier.dtos.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Project;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * A DTO for the {@link softclick.server.data.entities.Invoice} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreateAndUpdateDto  implements Serializable {
    private String date;
    private String total;
    private Client client;
    private Project project;
}
