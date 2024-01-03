package mozt.springframework.spring6restmvc.repository;

import mozt.springframework.spring6restmvc.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeverageRepository  extends JpaRepository<Beverage, UUID> {
}

