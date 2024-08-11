package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.CustomerDTO;
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
    public List<CustomerDTO> listAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(API_V1_CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(API_V1_CUSTOMER_PATH)
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.createCustomer(customerDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, API_V1_CUSTOMER_PATH + "/" + savedCustomerDTO.getId());

        return new ResponseEntity<>(savedCustomerDTO, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> updateById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {
        if (customerService.updateCustomerById(customerId, customerDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable("customerId") UUID customerId) {
        if (!customerService.deleteCustomerById(customerId)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(API_V1_CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> patchById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {
        customerService.patchCustomerById(customerId, customerDTO);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
