package softclick.server.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.Invoice;
import softclick.server.webtier.services.invoice.IInvoiceService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(@Qualifier("rmiInvoiceService") IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/invoices")
    public ResponseEntity<Object> index(){
        try{
            List<Invoice> invoices = invoiceService.getAllEntities();
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/invoices/{id}")
    public ResponseEntity<Object> single(@PathVariable String id){
        try{
            Invoice invoice = invoiceService.findEntityByKey(Long.valueOf(id));
            if (invoice == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/invoices")
    public ResponseEntity<Object> create(@RequestBody Invoice invoice){
        try{
            invoiceService.saveEntity(invoice);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/invoices")
    public ResponseEntity<Object> update(@RequestBody Invoice invoice){
        try{
            Invoice storedInvoice = invoiceService.findEntityByKey(invoice.getId());
            if (storedInvoice == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            System.out.println(invoice);
            invoiceService.saveEntity(invoice);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/invoices/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try{
            invoiceService.deleteEntity(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
