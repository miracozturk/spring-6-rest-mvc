package mozt.springframework.spring6restmvc.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BeverageControllerTest {

    @Autowired
    BeverageController bc;
    @Test
    void getBeverageById() {
        System.out.println(this.bc.getBeverageById(UUID.randomUUID()));
    }
}
