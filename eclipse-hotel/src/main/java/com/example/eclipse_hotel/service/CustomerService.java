package com.example.eclipse_hotel.service;

import com.example.eclipse_hotel.model.Customer;
import com.example.eclipse_hotel.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository; // Injecting the repository

    public List<Customer> findAll() {
        log.info("Fetching all customers");
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        log.info("Fetching customer with id: {}", id);
        return customerRepository.findById(id).orElse(null);
    }

    public Customer save(Customer customer) {
        log.info("Saving customer with id: {}", customer.getId());
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        log.info("Deleting customer with id: {}", id);
        customerRepository.deleteById(id);
    }
}
