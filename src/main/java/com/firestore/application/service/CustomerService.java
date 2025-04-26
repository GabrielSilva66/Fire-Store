package com.firestore.application.service;


import com.firestore.domain.customer.Customer;
import com.firestore.adapters.outbound.repositories.JpaCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final JpaCustomerRepository customerRepository;

    public CustomerService(JpaCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findActiveCustomers() {
        return customerRepository.findActiveCustomer();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deactivateCustomer(Long id) {
        customerRepository.updateCustomerStatus(id, false);
    }
}
