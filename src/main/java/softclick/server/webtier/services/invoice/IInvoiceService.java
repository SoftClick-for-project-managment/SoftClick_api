package softclick.server.webtier.services.invoice;

import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Invoice;
import softclick.server.webtier.services.IBaseService;

public interface IInvoiceService extends IBaseService<Invoice,Long> {
    void updateInvoice(Long id, Invoice newInvoice);

}
