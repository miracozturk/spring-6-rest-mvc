package mozt.springframework.spring6restmvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.services.BeverageService;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeverageController {
    private final BeverageService bs;

    public Beverage getBeverageById(UUID id){
        log.debug("Get Beverage by Id - In Controller Idd: " + id );
        return this.bs.getBeverageById(id);
    }

}
