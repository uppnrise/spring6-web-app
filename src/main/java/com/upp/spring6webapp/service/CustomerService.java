package com.upp.spring6webapp.service;

import com.upp.spring6webapp.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customerDTO);

    Boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customerDTO);
}
