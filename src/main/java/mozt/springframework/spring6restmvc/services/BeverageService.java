package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.Beverage;

import java.util.List;
import java.util.UUID;

public interface
BeverageService {
    Beverage getBeverageById(UUID beverageId);

    List<Beverage> listBeverages();

    Beverage saveNewBeverage(Beverage beverage);

    Beverage updateBeverageById(UUID beverageId, Beverage beverage);
}
