package mozt.springframework.spring6restmvc.mappers;

import mozt.springframework.spring6restmvc.entity.Beverage;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeverageMapper {
    BeverageDTO beverageToBeverageDto(Beverage b);
    Beverage beverageDtoToBeverage(BeverageDTO bDto);

}
