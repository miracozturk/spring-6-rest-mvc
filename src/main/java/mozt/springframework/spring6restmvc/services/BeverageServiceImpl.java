package mozt.springframework.spring6restmvc.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Beverage saveNewBeverage(Beverage b) {
        Beverage savedBeverage = Beverage.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .beverageName(b.getBeverageName())
                .beverageStyle(b.getBeverageStyle())
                .quantityOnHand(b.getQuantityOnHand())
                .upc(b.getUpc())
                .price(b.getPrice())
                .build();
        this.bMap.put(savedBeverage.getId(), savedBeverage);
        return savedBeverage;
    }

    @Override
    public Beverage updateBeverageById(UUID beverageId, Beverage newBeverage) {
        Beverage existing = this.bMap.get(beverageId);
        existing.setBeverageName(newBeverage.getBeverageName());
        existing.setBeverageStyle(newBeverage.getBeverageStyle());
        existing.setPrice(newBeverage.getPrice());
        existing.setUpdateDate(LocalDateTime.now());
        existing.setUpc(newBeverage.getUpc());
        existing.setVersion(existing.getVersion() + 1);
        existing.setQuantityOnHand(newBeverage.getQuantityOnHand());
        //normally it is not required to put The new object to the map again.
        return existing;
    }

    @Override
    public void deleteBeverageById(UUID beverageId) {
        this.bMap.remove(beverageId);
    }

    @Override
    public void patchBeverageById(UUID beverageId, Beverage beverage) {
        Beverage e = this.bMap.get(beverageId);
        if(StringUtils.hasText(beverage.getBeverageName())){
            e.setBeverageName(beverage.getBeverageName());
        }
        if (beverage.getBeverageStyle() != null) {
            e.setBeverageStyle(beverage.getBeverageStyle());
        }
        if (beverage.getPrice() != null) {
            e.setPrice(beverage.getPrice());
        }
        if (StringUtils.hasText(beverage.getUpc()) ) {
            e.setUpc(beverage.getUpc());
        }
        if (beverage.getQuantityOnHand() != null) {
            e.setQuantityOnHand(beverage.getQuantityOnHand());
        }
    }
}
