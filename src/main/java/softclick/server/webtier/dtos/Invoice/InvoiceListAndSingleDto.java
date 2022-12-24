package softclick.server.webtier.dtos.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Project;
import java.io.Serializable;
/**
 * A DTO for the {@link softclick.server.data.entities.Invoice} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListAndSingleDto implements Serializable {
    private Long id;
    private String date;
    private String total;
    private Client client;
    private Project project;
}
