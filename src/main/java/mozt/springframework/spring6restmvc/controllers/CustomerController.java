package mozt.springframework.spring6restmvc.controllers;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.Customer;
import mozt.springframework.spring6restmvc.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService cs;

    @PatchMapping(value=CUSTOMER_PATH_ID)
    public ResponseEntity patchBeverageById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){
        this.cs.patchCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerByID(@PathVariable("customerId") UUID customerId){
        this.cs.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        this.cs.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody Customer cust) {
        Customer cRet = this.cs.saveNewCustomer(cust);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, CUSTOMER_PATH + "/" +  cRet.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers(){
        log.debug("List customers in CustomerController:...");
        return this.cs.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable UUID customerId){
        log.debug("Getting the customer in Controller class by customerId : " + customerId);
        return  this.cs.getCustomerById(customerId);
    }
}
