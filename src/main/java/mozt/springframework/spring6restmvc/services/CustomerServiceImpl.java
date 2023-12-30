package mozt.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        CustomerDTO c1 = CustomerDTO.builder().customerName("CustomerName1")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        CustomerDTO c2 = CustomerDTO.builder().customerName("CustomerName2")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        CustomerDTO c3 = CustomerDTO.builder().customerName("CustomerName3")
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
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(this.customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID customerId) {
        return Optional.of(this.customerMap.get(customerId));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO cust) {


        CustomerDTO savedCustomerDTO = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(cust.getCustomerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(1)
                .build();
        this.customerMap.put(savedCustomerDTO.getId(), savedCustomerDTO);
        log.debug("Customer Id: {}", savedCustomerDTO.getId());
        return savedCustomerDTO;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO exists = this.customerMap.get(customerId);
        exists.setCustomerName(customerDTO.getCustomerName());
        exists.setLastModifiedDate(LocalDateTime.now());
        exists.setVersion(exists.getVersion() + 1);
        //no need reput to map this.customerMap.put(customerId, exist)
        log.debug("Customer version:" + exists.getVersion());
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        this.customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO c = this.customerMap.get(customerId);
        if(StringUtils.hasText(customerDTO.getCustomerName())){
            c.setCustomerName(customerDTO.getCustomerName());
        }
    }
}
