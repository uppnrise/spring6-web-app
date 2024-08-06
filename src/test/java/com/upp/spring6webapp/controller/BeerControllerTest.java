package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Beer;
import com.upp.spring6webapp.model.BeerStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void getBeerById() {

        System.out.println(beerController.getBeerById(UUID.randomUUID()));

    }

    @Test
    void saveNewBeer() {

        Beer alemBeere = Beer.builder()
                .beerStyle(BeerStyle.ALE)
                .beerName("AlemBeere").build();
        Beer savedBeer = beerController.createBeer(alemBeere).getBody();

        Assertions.assertNotNull(savedBeer.getId());
        Assertions.assertEquals(savedBeer, beerController.getBeerById(savedBeer.getId()));

    }
}