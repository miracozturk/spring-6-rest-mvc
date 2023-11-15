package mozt.springframework.spring6restmvc.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;


import java.util.ArrayList;
import java.util.UUID;



@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockmvc;

    @MockBean
    BeverageService bs;

    @Autowired
    ObjectMapper objectMapper;

    BeverageServiceImpl bsImp = new BeverageServiceImpl();

    @Test
    void testCreateNewBeer() throws JsonProcessingException {
        Beverage b = this.bsImp.listBeverages().get(0);
        System.out.println(objectMapper.writeValueAsString(b));

    }

    @Test
    void testListBeverages() throws Exception {
        given(bs.listBeverages()).willReturn(bsImp.listBeverages());
        mockmvc.perform(get("/api/v1/beverage").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON) )
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    public void getBeverageById() throws Exception {
        Beverage testB = this.bsImp.listBeverages().get(0);

        given(bs.getBeverageById(testB.getId())).willReturn(testB);
        this.mockmvc.perform(get("/api/v1/beverage/" + testB.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testB.getId().toString())))
                .andExpect(jsonPath("$.beverageName", is(testB.getBeverageName())));


    }

}
