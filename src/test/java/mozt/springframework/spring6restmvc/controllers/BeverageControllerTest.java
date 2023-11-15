package mozt.springframework.spring6restmvc.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mozt.springframework.spring6restmvc.services.BeverageService;
import mozt.springframework.spring6restmvc.services.BeverageServiceImpl;
import mozt.springframework.spring6restmvc.model.Beverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;


@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeverageService bs;

    @Autowired
    ObjectMapper objectMapper;

    BeverageServiceImpl bsImp;

    @BeforeEach
    void setup(){
        this.bsImp = new BeverageServiceImpl();
    }

    @Test
    void testCreateNewBeer() throws Exception {
        Beverage b = this.bsImp.listBeverages().get(0);
        b.setId(null);
        b.setVersion(null);
        given(this.bs.saveNewBeverage(any(Beverage.class))).willReturn(this.bsImp.listBeverages().get(1));
        mockMvc.perform(post("/api/v1/beverage")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(b))
                                )
                                .andExpect(status().isCreated())
                                .andExpect(header().exists("Location"));

        System.out.println(objectMapper.writeValueAsString(b));

    }

    @Test
    void testListBeverages() throws Exception {
        given(bs.listBeverages()).willReturn(bsImp.listBeverages());
        mockMvc.perform(get("/api/v1/beverage").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON) )
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    public void getBeverageById() throws Exception {
        Beverage testB = this.bsImp.listBeverages().get(0);

        given(bs.getBeverageById(testB.getId())).willReturn(testB);
        this.mockMvc.perform(get("/api/v1/beverage/" + testB.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testB.getId().toString())))
                .andExpect(jsonPath("$.beverageName", is(testB.getBeverageName())));


    }

}
