package mozt.springframework.spring6restmvc.repository;

import mozt.springframework.spring6restmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
