package softclick.server.webtier.services.invoice;

import softclick.server.data.entities.*;
import softclick.server.webtier.services.IBaseService;

import java.util.List;

public interface IInvoiceService extends IBaseService<Invoice,Long> {
    void updateInvoice(Long id, Invoice newInvoice);
    List<Invoice> serachInvoice( Project project, Client client);

}
