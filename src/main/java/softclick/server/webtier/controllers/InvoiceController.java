package softclick.server.webtier.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softclick.server.data.entities.*;
import softclick.server.webtier.dtos.Invoice.InvoiceCreateAndUpdateDto;
import softclick.server.webtier.dtos.Invoice.InvoiceListAndSingleDto;
import softclick.server.webtier.services.invoice.IInvoiceService;
import softclick.server.webtier.utils.exceptions.DataNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
    private final IInvoiceService invoiceService;
    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceController(@Qualifier("rmiInvoiceService") IInvoiceService invoiceService,ModelMapper modelMapper) {
        this.invoiceService = invoiceService;
        this.modelMapper=modelMapper;

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
            InvoiceListAndSingleDto invoiceDto = modelMapper.map(invoice, InvoiceListAndSingleDto.class);
            if (invoice == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/invoices")
    public ResponseEntity<Object> create(@RequestBody InvoiceCreateAndUpdateDto invoiceDto){
        try{
            Invoice invoice = modelMapper.map(invoiceDto,Invoice.class);
            invoiceService.saveEntity(invoice);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/invoices/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,@RequestBody InvoiceCreateAndUpdateDto invoiceDto){
        try{
            Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
            invoiceService.updateInvoice(Long.valueOf(id), invoice);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(DataNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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

    @PostMapping(value = "/invoices/search")
    public ResponseEntity<Object> search(@RequestBody Invoice invoice_searched){
        try{

            Project project =invoice_searched.getProject();
            Client client =invoice_searched.getClient();
            List<Invoice> invoices = invoiceService.serachInvoice(project,client);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
