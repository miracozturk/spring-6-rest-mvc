package mozt.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        Customer c1 = Customer.builder().customerName("CustomerName1")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        Customer c2 = Customer.builder().customerName("CustomerName2")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        Customer c3 = Customer.builder().customerName("CustomerName3")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        this.customerMap.put(c1.getId(),c1);
        this.customerMap.put(c2.getId(),c2);
        this.customerMap.put(c3.getId(),c3);
        System.out.println(this.customerMap);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(this.customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return this.customerMap.get(customerId);
    }

    @Override
    public Customer saveNewCustomer(Customer cust) {


        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(cust.getCustomerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(1)
                .build();
        this.customerMap.put(savedCustomer.getId(), savedCustomer);
        log.debug("Customer Id: {}", savedCustomer.getId());
        return savedCustomer;
    }
}
