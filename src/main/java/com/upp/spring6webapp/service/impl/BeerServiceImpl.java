package com.upp.spring6webapp.service.impl;

import com.upp.spring6webapp.model.BeerDTO;
import com.upp.spring6webapp.model.BeerStyle;
import com.upp.spring6webapp.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beerDTO1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beerDTO1.getId(), beerDTO1);
        beerMap.put(beerDTO2.getId(), beerDTO2);
        beerMap.put(beerDTO3.getId(), beerDTO3);
    }

    @Override
    public List<BeerDTO> getAllBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        BeerDTO savedBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
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
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO) {
        BeerDTO fetchedBeerDTO = beerMap.get(beerId);

        if (fetchedBeerDTO != null) {
            fetchedBeerDTO.setBeerName(beerDTO.getBeerName());
            fetchedBeerDTO.setUpc(beerDTO.getUpc());
            fetchedBeerDTO.setPrice(beerDTO.getPrice());
            fetchedBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
            fetchedBeerDTO.setUpdateDate(LocalDateTime.now());
        }
        return Optional.ofNullable(fetchedBeerDTO);
    }

    @Override
    public Boolean deleteBeerById(UUID beerId) {
        BeerDTO removed = beerMap.remove(beerId);

        return removed != null;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDTO) {
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

        return Optional.ofNullable(existingBeerDTO);
    }
}
