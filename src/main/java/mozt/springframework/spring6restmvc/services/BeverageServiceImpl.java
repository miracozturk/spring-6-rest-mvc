package mozt.springframework.spring6restmvc.services;

import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeverageServiceImpl extends BeverageService {

    @Override
    public Beverage getBeverageById(UUID id) {
        return Beverage.builder()
                .id(id)
                .version(1)
                .beverageName("Cocacola")
                .beverageStyle(BeverageStyle.ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
