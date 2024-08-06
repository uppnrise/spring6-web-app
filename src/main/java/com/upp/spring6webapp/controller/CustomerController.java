package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.Beer;
import com.upp.spring6webapp.model.Customer;
import com.upp.spring6webapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.upp.spring6webapp.controller.CustomerController.API_V1_CUSTOMER_PATH;

@RequestMapping(API_V1_CUSTOMER_PATH)
@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String API_V1_CUSTOMER_PATH = "/api/v1/customer";

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, API_V1_CUSTOMER_PATH + "/" + savedCustomer.getId());

        return new ResponseEntity<>(savedCustomer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomerById(customerId, customer);

        return updatedCustomer == null ? new ResponseEntity<>(updatedCustomer, HttpStatus.NO_CONTENT) : new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteById(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
