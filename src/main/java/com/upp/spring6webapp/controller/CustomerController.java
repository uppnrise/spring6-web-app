package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Customer;
import com.upp.spring6webapp.service.CustomerService;
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
public class CustomerController {
    public static final String API_V1_CUSTOMER_PATH = "/api/v1/customer";
    public static final String API_V1_CUSTOMER_PATH_ID = API_V1_CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(API_V1_CUSTOMER_PATH)
    public List<Customer> listAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(API_V1_CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(API_V1_CUSTOMER_PATH)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, API_V1_CUSTOMER_PATH + "/" + savedCustomer.getId());

        return new ResponseEntity<>(savedCustomer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomerById(customerId, customer);

        return updatedCustomer == null ? new ResponseEntity<>(updatedCustomer, HttpStatus.NO_CONTENT) : new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> deleteById(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> patchById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
