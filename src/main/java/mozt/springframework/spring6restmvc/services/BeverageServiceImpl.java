package mozt.springframework.spring6restmvc.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * It is written to practise the lombok library.
 * created by mozt
 * @author mozt
 */

@Slf4j
@Service
public class BeverageServiceImpl implements BeverageService {

    private Map<UUID, Beverage> bMap;


    public BeverageServiceImpl(){
        this.bMap = new HashMap<>();
        Beverage beverage1 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Cocacola")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        Beverage beverage2 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Fanta")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        Beverage beverage3 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Ayran")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("14.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        bMap.put(beverage1.getId(), beverage1);
        bMap.put(beverage2.getId(), beverage2);
        bMap.put(beverage3.getId(), beverage3);
    }


    @Override
    public Beverage getBeverageById(UUID id) {
        log.debug("Getting Beverages by Id in Service: Id: " + id);

        return this.bMap.get(id);
    }

    @Override
    public List<Beverage> listBeverages() {

        return new ArrayList<>(this.bMap.values());
    }
}
