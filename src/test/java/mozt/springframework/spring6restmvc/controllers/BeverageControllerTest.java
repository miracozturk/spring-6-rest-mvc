package mozt.springframework.spring6restmvc.controllers;


import mozt.springframework.spring6restmvc.services.BeverageService;
import org.junit.jupiter.api.Test;

import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.UUID;



@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockmvc;

    @MockBean
    BeverageService bs;

    @Test
    public void getBeverageById() throws Exception {
        this.mockmvc.perform(get("/api/v1/beverage/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
