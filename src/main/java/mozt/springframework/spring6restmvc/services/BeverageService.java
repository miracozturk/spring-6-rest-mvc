package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.Beverage;

import java.util.UUID;

public interface
BeverageService {
    Beverage getBeverageById(UUID id);
}
