package mozt.springframework.spring6restmvc.controllers;

import mozt.springframework.spring6restmvc.model.Customer;
import mozt.springframework.spring6restmvc.services.CustomerService;
import mozt.springframework.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService cs;

    CustomerServiceImpl custServImp = new CustomerServiceImpl();

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
