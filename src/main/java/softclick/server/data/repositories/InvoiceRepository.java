package softclick.server.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softclick.server.data.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findInvoiceByDate(String date);
}
