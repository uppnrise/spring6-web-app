package com.upp.spring6webapp.service;

import com.upp.spring6webapp.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerById(UUID id);

    List<Customer> getAllCustomers();
}
