package mozt.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * It is written to practise the lombok library.
 * created by mozt
 * @author mozt
 */
@Slf4j
@Service
public class BeverageServiceImpl implements BeverageService {

    @Override
    public Beverage getBeverageById(UUID id) {
        log.debug("getBeverageById service method was called.");
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
