package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Beer;
import com.upp.spring6webapp.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.upp.spring6webapp.controller.BeerController.API_V1_BEER_PATH;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(API_V1_BEER_PATH)
public class BeerController {
    public static final String API_V1_BEER_PATH = "/api/v1/beer";

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer){
        Beer saved = beerService.saveNewBeer(beer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", API_V1_BEER_PATH + "/" + saved.getId());

        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){

        Beer updatedBeer = beerService.updateBeerById(beerId, beer);

        return updatedBeer == null ? new ResponseEntity<>(updatedBeer, HttpStatus.NO_CONTENT) :new ResponseEntity<>(updatedBeer, HttpStatus.OK);

    }
}
