package mozt.springframework.spring6restmvc.controllers;

import jakarta.transaction.Transactional;
import mozt.springframework.spring6restmvc.entity.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import mozt.springframework.spring6restmvc.repository.BeverageRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//method 1 either this method that gives an order to the methods or use the @transaction and @rollback annotation
class BeverageControllerIntTest {

    @Autowired
    BeverageController beverageController;

    @Autowired
    BeverageRepository beverageRepository;


    @Test
//    @Order(1) method 1
    void testListBeverage() {
        List<BeverageDTO> beverageDTOS = this.beverageController.listBeverages();
        assertThat(beverageDTOS.size()).isEqualTo(3);
    }



    @Test
//    @Order(2) method 1
    @Transactional //method 2
    @Rollback //method 2
    void testListEmpty() {
        //empty the database
        this.beverageRepository.deleteAll();
        List<BeverageDTO> beverageDTOS = this.beverageController.listBeverages();
        assertThat(beverageDTOS.size()).isEqualTo(0);
    }

    @Test
    void testGetdBeverageById() {
        Beverage beverage = beverageRepository.findAll().get(0);
        BeverageDTO beverageById = this.beverageController.getBeverageById(beverage.getId());

        assertThat(beverageById).isNotNull();
        assertThat(beverageById.getBeverageName()).isEqualTo(beverage.getBeverageName());
    }

    @Test
    void testGetBeverageByIdExceptedException() {
       assertThrows(NotFoundException.class, ()->beverageController.getBeverageById(UUID.randomUUID()));
    }

    @Test
    void testBeverageSave() {
        BeverageDTO bDto = (BeverageDTO.builder()
                .beverageName("Wasser")
                .beverageStyle(BeverageStyle.ALE)
                .price(BigDecimal.ZERO)
                .quantityOnHand(100)).build();
        var responseEntity = this.beverageController.saveNewBeverage(bDto);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();


//        //get the new UUID
        String[] splittedPath = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID newUUID = UUID.fromString(splittedPath[4]);
        BeverageDTO newSavedBeverage  = beverageController.getBeverageById(newUUID);
        assertThat(newSavedBeverage).isNotNull();


    }
}
