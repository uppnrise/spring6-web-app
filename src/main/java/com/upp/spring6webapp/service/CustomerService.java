package com.upp.spring6webapp.service;

import com.upp.spring6webapp.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomerById(UUID customerId, CustomerDTO customerDTO);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDTO customerDTO);
}
