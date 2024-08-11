package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.entities.Customer;
import com.upp.spring6webapp.mappers.CustomerMapper;
import com.upp.spring6webapp.model.CustomerDTO;
import com.upp.spring6webapp.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testListAllCustomers() {
        List<CustomerDTO> customerDTOS = customerController.listAllCustomers();
        assertThat(customerDTOS.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();

        List<CustomerDTO> customerDTOS = customerController.listAllCustomers();
        assertThat(customerDTOS.size()).isEqualTo(0);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
        assertThat(customerDTO.getId()).isEqualTo(customer.getId());
    }

    @Test
    void testCustomerIdNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Rollback
    @Transactional
    @Test
    void testNewCustomerTest() {
        CustomerDTO newCustomerDTO = CustomerDTO.builder()
                .name("New Customer")
                .build();

        ResponseEntity<CustomerDTO> responseEntity = customerController.createCustomer(newCustomerDTO);
        String locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/")[4];
        UUID savedUUID = UUID.fromString(locationUUID);
        Customer customer = customerRepository.findById(savedUUID).get();

        assertThat(customer).isNotNull();
    }

    @Test
    void testUpdateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String name = "UPDATED!";
        customerDTO.setName(name);

        ResponseEntity<CustomerDTO> responseEntity = customerController.updateById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getName()).isEqualTo(name);
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class,
                () -> customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build()));
    }
}