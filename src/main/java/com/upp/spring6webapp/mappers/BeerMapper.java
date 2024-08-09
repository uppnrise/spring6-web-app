package com.upp.spring6webapp.mappers;

import com.upp.spring6webapp.entities.Beer;
import com.upp.spring6webapp.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);

}
