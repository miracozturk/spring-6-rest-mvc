package mozt.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
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

    private Map<UUID, BeverageDTO> bMap;


    public BeverageServiceImpl(){
        this.bMap = new HashMap<>();
        BeverageDTO beverageDTO1 =
                BeverageDTO.builder()
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
        BeverageDTO beverageDTO2 =
                BeverageDTO.builder()
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
        BeverageDTO beverageDTO3 =
                BeverageDTO.builder()
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
        bMap.put(beverageDTO1.getId(), beverageDTO1);
        bMap.put(beverageDTO2.getId(), beverageDTO2);
        bMap.put(beverageDTO3.getId(), beverageDTO3);
    }


    @Override
    public Optional<BeverageDTO> getBeverageById(UUID id) {
        log.debug("Getting Beverages by Id in Service: Id: " + id);

        return Optional.of(this.bMap.get(id));
    }

    @Override
    public List<BeverageDTO> listBeverages() {

        return new ArrayList<>(this.bMap.values());
    }

    @Override
    public BeverageDTO saveNewBeverage(BeverageDTO b) {
        BeverageDTO savedBeverageDTO = BeverageDTO.builder()
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
        this.bMap.put(savedBeverageDTO.getId(), savedBeverageDTO);
        return savedBeverageDTO;
    }

    @Override
    public BeverageDTO updateBeverageById(UUID beverageId, BeverageDTO newBeverageDTO) {
        BeverageDTO existing = this.bMap.get(beverageId);
        existing.setBeverageName(newBeverageDTO.getBeverageName());
        existing.setBeverageStyle(newBeverageDTO.getBeverageStyle());
        existing.setPrice(newBeverageDTO.getPrice());
        existing.setUpdateDate(LocalDateTime.now());
        existing.setUpc(newBeverageDTO.getUpc());
        existing.setVersion(existing.getVersion() + 1);
        existing.setQuantityOnHand(newBeverageDTO.getQuantityOnHand());
        //normally it is not required to put The new object to the map again.
        return existing;
    }

    @Override
    public void deleteBeverageById(UUID beverageId) {
        this.bMap.remove(beverageId);
    }

    @Override
    public void patchBeverageById(UUID beverageId, BeverageDTO beverageDTO) {
        BeverageDTO e = this.bMap.get(beverageId);
        if(StringUtils.hasText(beverageDTO.getBeverageName())){
            e.setBeverageName(beverageDTO.getBeverageName());
        }
        if (beverageDTO.getBeverageStyle() != null) {
            e.setBeverageStyle(beverageDTO.getBeverageStyle());
        }
        if (beverageDTO.getPrice() != null) {
            e.setPrice(beverageDTO.getPrice());
        }
        if (StringUtils.hasText(beverageDTO.getUpc()) ) {
            e.setUpc(beverageDTO.getUpc());
        }
        if (beverageDTO.getQuantityOnHand() != null) {
            e.setQuantityOnHand(beverageDTO.getQuantityOnHand());
        }
    }
}
