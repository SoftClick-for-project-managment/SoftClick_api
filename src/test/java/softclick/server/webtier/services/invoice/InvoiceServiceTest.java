package softclick.server.webtier.services.invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softclick.server.data.entities.*;
import softclick.server.data.repositories.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {
    @Mock private InvoiceRepository invoiceRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private ClientRepository clientRepository;
    private IInvoiceService serviceUnderTest;
    @BeforeEach
    void setUp() {
        serviceUnderTest = new InvoiceService(invoiceRepository,clientRepository,projectRepository);
    }
    @Test
    void itShouldVerifyInvoiceAdded() {
        // given
        Client client=new Client("cccc","ccc","ccc","06 37 94","soft","temara","maroc");
        Project project=new Project();
        Invoice invoice=new Invoice("12-7-2022","40000",client,project);
        // when
        serviceUnderTest.saveEntity(invoice);
        // then
        ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
        verify(invoiceRepository).save(invoiceArgumentCaptor.capture());
        Invoice capturedInvoice= invoiceArgumentCaptor.getValue();
        assertThat(capturedInvoice).isEqualTo(invoice);
    }
    @Test
    void itShouldVerifyInvoiceDateUpdated() {
        // given
        Invoice newInvoice = new Invoice("02-2-2019",null,null,null);
        Client client=new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc");
        Project project=new Project();
        Invoice invoice = new Invoice("03-03-2022","3000",client,project);
        invoice.setId(1L);
        Invoice oldInvoiceCopy = new Invoice("03-03-2022","3000",client,project);
        oldInvoiceCopy.setId(1L);
        given(invoiceRepository.getReferenceById(1L))
                .willReturn(invoice);
        // when
        serviceUnderTest.updateInvoice(1L, newInvoice);
        // then
        verify(invoiceRepository).save(any());
        assertThat(invoice.getDate()).isEqualTo(newInvoice.getDate());
        System.out.println(invoice);
        assertThat(reflectionCompare(invoice, oldInvoiceCopy, "date")).isEqualTo(0);
        System.out.println(oldInvoiceCopy);
    }
    @Test
    void itShouldVerifyInvoiceTotalUpdated() {
        // given
        Invoice newInvoice = new Invoice(null,"3000",null,null);
        Client client=new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc");
        Project project=new Project();
        Invoice invoice = new Invoice("03-03-2022","6000",client,project);
        invoice.setId(1L);
        Invoice oldInvoiceCopy = new Invoice("03-03-2022","6000",client,project);
        oldInvoiceCopy.setId(1L);
        given(invoiceRepository.getReferenceById(1L))
                .willReturn(invoice);
        // when
        serviceUnderTest.updateInvoice(1L, newInvoice);
        // then
        verify(invoiceRepository).save(any());
        assertThat(invoice.getTotal()).isEqualTo(newInvoice.getTotal());
        System.out.println(invoice);
        assertThat(reflectionCompare(invoice, oldInvoiceCopy, "total")).isEqualTo(0);
        System.out.println(oldInvoiceCopy);
    }
    @Test
    void canDeleteInvoice() {
        // given
        Client client=new Client("abouzbiba","wafae","wafae.abouzbiba@gmail.com","06 37 94","soft","temara","maroc");
        Project project=new Project();
        Invoice invoice = new Invoice(
                "03-03-2022","3000",client,project);
        invoice.setId(1L);
        given(invoiceRepository.getReferenceById(1L)).willReturn(invoice);
        // when
        serviceUnderTest.deleteEntity(1L);

        // then
        verify(invoiceRepository).delete(invoiceRepository.getReferenceById(1L));
}
    @Test
    void canGetAllInvoices() {
        // When
        serviceUnderTest.getAllEntities();
        // Then
        verify(invoiceRepository).findAll();
}


}
