package com.upp.spring6webapp.service;

import com.upp.spring6webapp.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    Beer updateBeerById(UUID beerId, Beer beer);

    Beer deleteBeerById(UUID beerId);
}
