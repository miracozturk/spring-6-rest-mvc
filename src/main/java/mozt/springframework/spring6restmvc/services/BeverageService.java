package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.Beverage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface
BeverageService {
    Optional<Beverage> getBeverageById(UUID beverageId);

    List<Beverage> listBeverages();

    Beverage saveNewBeverage(Beverage beverage);

    Beverage updateBeverageById(UUID beverageId, Beverage beverage);

    void deleteBeverageById(UUID beverageId);

    void patchBeverageById(UUID beverageId, Beverage beverage);
}
