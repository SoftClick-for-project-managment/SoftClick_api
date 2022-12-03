package softclick.server.webtier.services.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Invoice;
import softclick.server.data.repositories.InvoiceRepository;
import softclick.server.webtier.services.BaseService;

import javax.transaction.Transactional;

@Service @Transactional @Slf4j @Qualifier("rmiInvoiceService")
public class InvoiceService extends BaseService<Invoice,Long> implements IInvoiceService {
   /* public static InvoiceService init(ApplicationContext context){
        InvoiceRepository invoiceRepository = context.getBean(InvoiceRepository.class);
        return new InvoiceService(invoiceRepository);
    }*/

    protected InvoiceService(InvoiceRepository repository) {
        super(repository);
    }
}
