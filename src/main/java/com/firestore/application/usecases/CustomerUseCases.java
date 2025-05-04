package com.firestore.application.usecases;

import com.firestore.domain.customer.Customer;

import java.util.List;

public interface CustomerUseCases {
    public List<Customer> findActiveCustomers();

    public Customer findById(Long id);

    public Customer save(Customer customer);

    public void deactivateCustomer(Long id);

}
