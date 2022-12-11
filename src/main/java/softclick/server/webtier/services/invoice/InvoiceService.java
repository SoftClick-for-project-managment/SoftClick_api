package softclick.server.webtier.services.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Employee;
import softclick.server.data.entities.Invoice;
import softclick.server.data.entities.Task;
import softclick.server.data.repositories.ClientRepository;
import softclick.server.data.repositories.InvoiceRepository;
import softclick.server.data.repositories.ProjectRepository;
import softclick.server.webtier.services.BaseService;
import softclick.server.webtier.utils.exceptions.BusinessException;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service @Transactional @Slf4j @Qualifier("rmiInvoiceService")
public class InvoiceService extends BaseService<Invoice,Long> implements IInvoiceService {
   /* public static InvoiceService init(ApplicationContext context){
        InvoiceRepository invoiceRepository = context.getBean(InvoiceRepository.class);
        return new InvoiceService(invoiceRepository);
    }*/
   private final InvoiceRepository invoiceRepository;
   private final ClientRepository clientRepository;
   private final ProjectRepository projectRepository;
    protected InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository, ProjectRepository projectRepository) {
        super(invoiceRepository);
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void updateInvoice(Long id, Invoice newInvoice) {
        log.info("Updating invoice with id: {}", id.toString());
        Invoice invoice = invoiceRepository.getReferenceById(id);
        if (invoice == null)
            throw new EntityNotFoundException();
        if(newInvoice.getDate()!= null)
        invoice.setDate(newInvoice.getDate());
        if(newInvoice.getTotal()!= null)
        invoice.setTotal(newInvoice.getTotal());
        if(newInvoice.getProject()!= null)
        invoice.setProject(newInvoice.getProject());
        if(newInvoice.getClient()!= null)
        invoice.setClient(newInvoice.getClient());
        invoiceRepository.save(invoice);
    }


}
