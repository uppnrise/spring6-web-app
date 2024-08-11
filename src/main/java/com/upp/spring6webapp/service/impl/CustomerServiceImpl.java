package com.upp.spring6webapp.service.impl;

import com.upp.spring6webapp.model.CustomerDTO;
import com.upp.spring6webapp.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerDTO savedBuild = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customerDTO.getName())
                .version(customerDTO.getVersion())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedBuild.getId(), savedBuild);

        return savedBuild;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);

        if (fetchedCustomerDTO != null) {
            fetchedCustomerDTO.setName(customerDTO.getName());
            fetchedCustomerDTO.setUpdatedDate(LocalDateTime.now());
        }

        return Optional.ofNullable(fetchedCustomerDTO);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = customerMap.get(customerId);

        if (existingCustomerDTO != null) {
            if (StringUtils.hasText(customerDTO.getName())) {
                existingCustomerDTO.setName(customerDTO.getName());
            }
            existingCustomerDTO.setUpdatedDate(LocalDateTime.now());
        }
    }
}
