package mozt.springframework.spring6restmvc.services;

import lombok.RequiredArgsConstructor;
import mozt.springframework.spring6restmvc.mappers.BeverageMapper;
import mozt.springframework.spring6restmvc.model.BeverageDTO;
import mozt.springframework.spring6restmvc.repository.BeverageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeverageServiceJpaImpl implements BeverageService {
    private final BeverageRepository br;
    private final BeverageMapper beverageMapper;

    @Override
    public Optional<BeverageDTO> getBeverageById(UUID beverageId) {
        return Optional.ofNullable(beverageMapper.beverageToBeverageDto(br.findById(beverageId).orElse(null)));
    }

    @Override
    public List<BeverageDTO> listBeverages() {
        return this.br.findAll().stream().map(beverageMapper::beverageToBeverageDto).collect(Collectors.toList());
    }

    @Override
    public BeverageDTO saveNewBeverage(BeverageDTO beverageDTO) {
         return beverageMapper.beverageToBeverageDto(br.save(beverageMapper.beverageDtoToBeverage(beverageDTO)));
    }

    @Override
    public BeverageDTO updateBeverageById(UUID beverageId, BeverageDTO beverageDTO) {
        return null;
    }

    @Override
    public void deleteBeverageById(UUID beverageId) {

    }

    @Override
    public void patchBeverageById(UUID beverageId, BeverageDTO beverageDTO) {

    }
}
