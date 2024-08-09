package com.upp.spring6webapp.mappers;

import com.upp.spring6webapp.entities.Customer;
import com.upp.spring6webapp.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);

}
