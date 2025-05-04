package com.firestore.domain.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    void deleteById(Long id);
    List<Customer> findAll();
    List<Customer> findActiveCustomer();
    void updateCustomerStatus(Long id, boolean status);
}
