package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.BeverageDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface
BeverageService {
    Optional<BeverageDTO> getBeverageById(UUID beverageId);

    List<BeverageDTO> listBeverages();

    BeverageDTO saveNewBeverage(BeverageDTO beverageDTO);

    BeverageDTO updateBeverageById(UUID beverageId, BeverageDTO beverageDTO);

    void deleteBeverageById(UUID beverageId);

    void patchBeverageById(UUID beverageId, BeverageDTO beverageDTO);
}
