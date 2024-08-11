package com.upp.spring6webapp.controller;

import com.upp.spring6webapp.model.CustomerDTO;
import com.upp.spring6webapp.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

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
}