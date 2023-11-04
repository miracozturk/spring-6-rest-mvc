package mozt.springframework.spring6restmvc.controllers;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Customer;
import mozt.springframework.spring6restmvc.services.CustomerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService cs;


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
