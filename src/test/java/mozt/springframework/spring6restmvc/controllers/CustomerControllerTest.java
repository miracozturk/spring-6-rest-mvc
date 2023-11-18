package mozt.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.Customer;
import mozt.springframework.spring6restmvc.services.CustomerService;
import mozt.springframework.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService cs;

    CustomerServiceImpl custServImp = new CustomerServiceImpl();

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testDeleteBeverage() throws Exception {

        Customer c = this.custServImp.listCustomers().get(0);

        mockMvc.perform(delete("/api/v1/customer/" + c.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(this.cs).deleteCustomerById(uuidCaptor.capture());

        assertThat(c.getId()).isEqualTo(uuidCaptor.getValue());

    }


    @Test
    void testCreateNewCustomer() throws Exception { //these methods just for testing the controller methods. Therefore We mock up the service layer.
        Customer c = this.custServImp.listCustomers().get(0); // get any customer to produce a json
        c.setCustomerName("new Customer");
        c.setVersion(null);
        c.setId(null);

        given(this.cs.saveNewCustomer(any(Customer.class))).willReturn(this.custServImp.listCustomers().get(0));

        mockMvc.perform(post("/api/v1/customer")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));//if we write the "Locationxyz", then we will get an exception.
    }

    @Test
    void listAllCustomers() throws Exception{


        given(this.cs.listCustomers()).willReturn(this.custServImp.listCustomers());

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }

    @Test
    void getCustomerById() throws Exception {
        Customer c = custServImp.listCustomers().get(0);

        given(cs.getCustomerById(c.getId())).willReturn(c);

        mockMvc.perform(get("/api/v1/customer/" + c.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName", is(c.getCustomerName())));


    }
}
