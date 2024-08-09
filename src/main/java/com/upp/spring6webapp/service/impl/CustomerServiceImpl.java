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
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();

        customerMap.put(customerDTO1.getId(), customerDTO1);
        customerMap.put(customerDTO2.getId(), customerDTO2);
        customerMap.put(customerDTO3.getId(), customerDTO3);
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return customerMap.get(id);
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
    public CustomerDTO updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);

        if (fetchedCustomerDTO != null) {
            fetchedCustomerDTO.setName(customerDTO.getName());
            fetchedCustomerDTO.setUpdatedDate(LocalDateTime.now());
        }
        return fetchedCustomerDTO;
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
