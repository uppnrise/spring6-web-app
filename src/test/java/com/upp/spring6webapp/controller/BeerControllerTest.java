package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Beer;
import com.upp.spring6webapp.model.BeerStyle;
import com.upp.spring6webapp.service.BeerService;
import com.upp.spring6webapp.service.impl.BeerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);

        BDDMockito.given(beerService.getBeerById(ArgumentMatchers.any(UUID.class))).willReturn(testBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveNewBeer() {

//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer"))
//
//        Beer alemBeere = Beer.builder()
//                .beerStyle(BeerStyle.ALE)
//                .beerName("AlemBeere").build();
//        Beer savedBeer = beerController.createBeer(alemBeere).getBody();
//
//        Assertions.assertNotNull(savedBeer.getId());
//        Assertions.assertEquals(savedBeer, beerController.getBeerById(savedBeer.getId()));

    }
}