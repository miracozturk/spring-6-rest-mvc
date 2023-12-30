package mozt.springframework.spring6restmvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
import mozt.springframework.spring6restmvc.services.BeverageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeverageController {
    public static final String BEVERAGE_PATH = "/api/v1/beverage";
    public static final String BEVERAGE_PATH_ID = BEVERAGE_PATH + "/{beverageId}";

    private final BeverageService bs;

    @PatchMapping(value=BEVERAGE_PATH_ID)
    public ResponseEntity patchBeverageById(@PathVariable("beverageId") UUID beverageId, @RequestBody BeverageDTO beverageDTO){
        this.bs.patchBeverageById(beverageId, beverageDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEVERAGE_PATH_ID)
    public ResponseEntity deleteBeverageByID(@PathVariable("beverageId") UUID beverageId){
        this.bs.deleteBeverageById(beverageId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEVERAGE_PATH_ID)
    public ResponseEntity updateBeverageByID(@PathVariable("beverageId") UUID beverageId, @RequestBody BeverageDTO beverageDTO){
        BeverageDTO bSaved = this.bs.updateBeverageById(beverageId, beverageDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/beverage/" + bSaved.getId());

        ResponseEntity responseEntity = new ResponseEntity(headers, HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @PostMapping(BEVERAGE_PATH)
    public ResponseEntity handlePost(@RequestBody BeverageDTO beverageDTO) {
        BeverageDTO bRet = this.bs.saveNewBeverage(beverageDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/beverage" + bRet.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(path = BEVERAGE_PATH, method = RequestMethod.GET)
    public List<BeverageDTO> listBeverages() {
        return this.bs.listBeverages();
    }

    @RequestMapping(value = BEVERAGE_PATH_ID, method = RequestMethod.GET)
    public BeverageDTO getBeverageById(@PathVariable("beverageId") UUID beverageId) { //or It can be just beverageId written without requestParam
        log.debug("Get Beverage by Id - In Controller Id: " + beverageId);
        return this.bs.getBeverageById(beverageId).orElseThrow(NotFoundException::new);
    }

    //to catch all exception that is throwed by methods of this class
    //the annotation and the method below can be used. We have more efficient way to do this.
    //Hence it is commented to be used in future.
//    @ExceptionHandler()
//    public ResponseEntity handleNotFoundException(){
//        return ResponseEntity.notFound().build();
//    }

}
