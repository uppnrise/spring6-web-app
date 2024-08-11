package com.upp.spring6webapp.service.impl;

import com.upp.spring6webapp.model.BeerDTO;
import com.upp.spring6webapp.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        BeerDTO savedBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beerDTO.getBeerName())
                .beerStyle(beerDTO.getBeerStyle())
                .quantityOnHand(beerDTO.getQuantityOnHand())
                .upc(beerDTO.getUpc())
                .price(beerDTO.getPrice())
                .build();

        beerMap.put(savedBeerDTO.getId(), savedBeerDTO);

        return savedBeerDTO;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
        BeerDTO fetchedBeerDTO = beerMap.get(beerId);

        if (fetchedBeerDTO != null) {
            fetchedBeerDTO.setBeerName(beerDTO.getBeerName());
            fetchedBeerDTO.setUpc(beerDTO.getUpc());
            fetchedBeerDTO.setPrice(beerDTO.getPrice());
            fetchedBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
            fetchedBeerDTO.setUpdateDate(LocalDateTime.now());
        }
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beerDTO) {
        BeerDTO existingBeerDTO = beerMap.get(beerId);

        if (existingBeerDTO != null) {
            if (StringUtils.hasText(beerDTO.getBeerName())) {
                existingBeerDTO.setBeerName(beerDTO.getBeerName());
            }
            if(beerDTO.getBeerStyle() != null) {
                existingBeerDTO.setBeerStyle(beerDTO.getBeerStyle());
            }
            if (StringUtils.hasText(beerDTO.getUpc())) {
                existingBeerDTO.setUpc(beerDTO.getUpc());
            }
            if (beerDTO.getPrice() != null) {
                existingBeerDTO.setPrice(beerDTO.getPrice());
            }
            if (beerDTO.getQuantityOnHand() != null) {
                existingBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
            }
            existingBeerDTO.setUpdateDate(LocalDateTime.now());
        }
    }
}
