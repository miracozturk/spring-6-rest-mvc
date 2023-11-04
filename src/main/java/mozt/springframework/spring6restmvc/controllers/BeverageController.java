package mozt.springframework.spring6restmvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.services.BeverageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class BeverageController {

    private final BeverageService bs;

    @RequestMapping("/api/v1/beverage")
    public List<Beverage> listBeverages(){
        return this.bs.listBeverages();
    }

    public Beverage getBeverageById(UUID id){
        log.debug("Get Beverage by Id - In Controller Idd: " + id );
        return this.bs.getBeverageById(id);
    }



}
