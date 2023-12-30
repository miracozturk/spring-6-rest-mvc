package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();
    Optional<Customer> getCustomerById(UUID customerId);

    Customer saveNewCustomer(Customer cust);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);

}
