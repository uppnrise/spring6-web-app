package com.upp.spring6webapp.service;

import com.upp.spring6webapp.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO);

    Boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDTO);
}
