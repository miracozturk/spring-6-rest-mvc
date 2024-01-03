package mozt.springframework.spring6restmvc.mappers;

import mozt.springframework.spring6restmvc.entity.Customer;
import mozt.springframework.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOtoCustomer(CustomerDTO customerDto);
    CustomerDTO customertoCustomerDTO(Customer customerDto);

}
