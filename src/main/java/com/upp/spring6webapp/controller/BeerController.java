package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Beer;
import com.upp.spring6webapp.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    public static final String API_V1_BEER_PATH = "/api/v1/beer";
    public static final String API_V1_BEER_PATH_ID = API_V1_BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @GetMapping(API_V1_BEER_PATH)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(API_V1_BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }

    @PostMapping(API_V1_BEER_PATH)
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer) {
        Beer saved = beerService.saveNewBeer(beer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", API_V1_BEER_PATH + "/" + saved.getId());

        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<Beer> deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeerById(beerId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<Beer> patchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
