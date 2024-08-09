package com.upp.spring6webapp.repositories;

import com.upp.spring6webapp.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("New Beer")
                .build());

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
        assertThat(savedBeer.getBeerName()).isEqualTo("New Beer");
    }
}
