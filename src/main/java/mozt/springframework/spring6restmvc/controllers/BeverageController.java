package mozt.springframework.spring6restmvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.services.BeverageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beverage")
public class BeverageController {

    private final BeverageService bs;

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beverage beverage) {
        Beverage bRet = this.bs.saveNewBeverage(beverage);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beverage> listBeverages() {
        return this.bs.listBeverages();
    }

    @RequestMapping(value = "{beverageId}", method = RequestMethod.GET)
    public Beverage getBeverageById(@PathVariable("beverageId") UUID beverageId) { //or It can be just beverageId written without requestParam
        log.debug("Get Beverage by Id - In Controller Id: " + beverageId);
        return this.bs.getBeverageById(beverageId);
    }


}
