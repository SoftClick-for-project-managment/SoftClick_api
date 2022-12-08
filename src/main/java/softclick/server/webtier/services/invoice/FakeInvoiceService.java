package softclick.server.webtier.services.invoice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softclick.server.data.entities.Client;
import softclick.server.data.entities.Invoice;
import softclick.server.data.entities.Project;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("rmiFakeInvoiceService")
public class FakeInvoiceService implements IInvoiceService {

    private List<Invoice> invoices = new ArrayList<>();
    private Client client1=new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc");
    private Client client2=new Client("cccc","ccc","ccc","06 37 94","soft","temara","maroc");
    Project project=new Project();
    public FakeInvoiceService(){
        invoices.add(new Invoice("12-7-2022","40000",client1,project));
        invoices.add(new Invoice("11-6-2020","400",client2,project));
    }
    @Override
    public void saveEntity(Invoice entity) {

    }

    @Override
    public Invoice findEntityByKey(Long aLong) {
        return invoices.get(aLong.intValue());
    }

    @Override
    public List<Invoice> getAllEntities() {
        return invoices;
    }

    @Override
    public void deleteEntity(Long aLong) {

    }

    @Override
    public void deleteAllEntities(List<Long> longs) {

    }

    @Override
    public void updateInvoice(Long id, Invoice newInvoice) {

    }
}
