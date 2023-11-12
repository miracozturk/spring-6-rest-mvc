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
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService cs;


    @PatchMapping(value="{customerId}")
    public ResponseEntity patchBeverageById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){
        this.cs.patchCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomerByID(@PathVariable("customerId") UUID customerId){
        this.cs.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        this.cs.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer cust) {
        Customer cRet = this.cs.saveNewCustomer(cust);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer/" + cRet.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Customer> listCustomers(){
        log.debug("List customers in CustomerController:...");
        return this.cs.listCustomers();
    }

    @RequestMapping(value="{customerId}", method= RequestMethod.GET)
    public Customer getCustomerById(@PathVariable UUID customerId){
        log.debug("Getting the customer in Controller class by customerId : " + customerId);
        return  this.cs.getCustomerById(customerId);
    }



}
