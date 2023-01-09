package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softclick.server.data.entities.*;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findInvoiceByDate(String date);

    @Query("SELECT e FROM Invoice as e WHERE ( :project is null or e.project = :project  ) and " +
            " ( :client is null or e.client = :client  ) ")
    List<Invoice> searchInvoice(Project project , Client client);
}
