package mozt.springframework.spring6restmvc.controllers;


import mozt.springframework.spring6restmvc.services.BeverageService;
import mozt.springframework.spring6restmvc.services.BeverageServiceImpl;
import mozt.springframework.spring6restmvc.model.Beverage;
import org.junit.jupiter.api.Test;

import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import java.util.UUID;



@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockmvc;

    @MockBean
    BeverageService bs;

    BeverageServiceImpl bsImp = new BeverageServiceImpl();

    @Test
    public void getBeverageById() throws Exception {
        Beverage b = this.bsImp.listBeverages().get(0);

        given(bs.getBeverageById(any(UUID.class))).willReturn(b);
        this.mockmvc.perform(get("/api/v1/beverage/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
