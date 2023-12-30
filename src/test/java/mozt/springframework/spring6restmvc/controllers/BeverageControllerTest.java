package mozt.springframework.spring6restmvc.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
import mozt.springframework.spring6restmvc.services.BeverageService;
import mozt.springframework.spring6restmvc.services.BeverageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeverageService bs;

    @Autowired
    ObjectMapper objectMapper;
    //objectMapper.findAndRegisterModules(); for example to import DateTime object modules.

    @Captor
    ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    ArgumentCaptor<BeverageDTO> beverageCaptor;

    BeverageServiceImpl bsImp;

    @BeforeEach
    void setup(){
        this.bsImp = new BeverageServiceImpl();
    }

    @Test
    void testPatchBeverage() throws Exception{
        BeverageDTO b = this.bsImp.listBeverages().get(0);
        b.setBeverageName("New Beverage");

        mockMvc.perform(patch(BeverageController.BEVERAGE_PATH_ID, b.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(b))
        ).andExpect(status().isNoContent());

        verify(this.bs).patchBeverageById(uuidCaptor.capture(), beverageCaptor.capture());

        assertThat(b.getId()).isEqualTo(uuidCaptor.getValue());
        assertThat(b.getBeverageName()).isEqualTo(beverageCaptor.getValue().getBeverageName());
    }

    @Test
    void testDeleteBeverage() throws Exception {

        BeverageDTO b = this.bsImp.listBeverages().get(0);

        mockMvc.perform(delete(BeverageController.BEVERAGE_PATH_ID, b.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(this.bs).deleteBeverageById(uuidCaptor.capture());

        assertThat(b.getId()).isEqualTo(uuidCaptor.getValue());

    }

    @Test
    void testUpdateBeverage() throws Exception {
        BeverageDTO b = this.bsImp.listBeverages().get(0);
        //mocking up
        //there is  no need below method???
        given(this.bs.updateBeverageById(any(UUID.class), any(BeverageDTO.class))).willReturn(b);
        this.mockMvc.perform(put(BeverageController.BEVERAGE_PATH_ID, b.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(b)))
                .andExpect(status().isNoContent())
                .andExpect(header().exists("Location"));
        verify(bs).updateBeverageById(any(UUID.class),any(BeverageDTO.class));//checks if this method has been called 1 time.
    }


    @Test
    void testCreateNewBeer() throws Exception {
        BeverageDTO b = this.bsImp.listBeverages().get(0);
        b.setId(null);
        b.setVersion(null);
        given(this.bs.saveNewBeverage(any(BeverageDTO.class))).willReturn(this.bsImp.listBeverages().get(1));
        mockMvc.perform(post(BeverageController.BEVERAGE_PATH)
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
        mockMvc.perform(get(BeverageController.BEVERAGE_PATH).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON) )
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testgetBeverageByIdNotFound() throws Exception {
        given(bs.getBeverageById(any(UUID.class))).willThrow(NotFoundException.class);
        this.mockMvc.perform(get(BeverageController.BEVERAGE_PATH_ID, UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getBeverageById() throws Exception {
        BeverageDTO testB = this.bsImp.listBeverages().get(0);

        given(bs.getBeverageById(testB.getId())).willReturn(Optional.of(testB));
        this.mockMvc.perform(get(BeverageController.BEVERAGE_PATH_ID, testB.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testB.getId().toString())))
                .andExpect(jsonPath("$.beverageName", is(testB.getBeverageName())));


    }

}
