package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.BeerDTO;
import com.upp.spring6webapp.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public List<BeerDTO> listBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping(API_V1_BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(API_V1_BEER_PATH)
    public ResponseEntity<BeerDTO> createBeer(@Validated @RequestBody BeerDTO beerDTO) {
        BeerDTO saved = beerService.createBeer(beerDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", API_V1_BEER_PATH + "/" + saved.getId());

        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<BeerDTO> updateById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDTO beerDTO) {

        if (beerService.updateBeerById(beerId, beerDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<BeerDTO> deleteById(@PathVariable("beerId") UUID beerId) {
        if (!beerService.deleteBeerById(beerId)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(API_V1_BEER_PATH_ID)
    public ResponseEntity<BeerDTO> patchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {
        if(beerService.patchBeerById(beerId, beerDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
