package com.firestore.application.service;


import com.firestore.application.usecases.CustomerUseCases;
import com.firestore.domain.customer.Customer;
import com.firestore.adapters.outbound.repositories.JpaCustomerRepository;
import com.firestore.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerUseCases {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findActiveCustomers() {
        return customerRepository.findActiveCustomer();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deactivateCustomer(Long id) {
        customerRepository.updateCustomerStatus(id, false);
    }
}
