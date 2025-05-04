package com.firestore.adapters.outbound.repositories.imp;


import com.firestore.adapters.outbound.entities.JpaCustomerEntity;
import com.firestore.adapters.outbound.mapper.CustomerMapper;
import com.firestore.adapters.outbound.repositories.JpaCustomerRepository;
import com.firestore.domain.customer.Customer;
import com.firestore.domain.customer.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class CustomerRepositoryImp implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;


    public CustomerRepositoryImp(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<JpaCustomerEntity> entity = jpaCustomerRepository.findById(id);
        return entity.map(CustomerMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        JpaCustomerEntity entity = CustomerMapper.toEntity(customer);
        this.jpaCustomerRepository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaCustomerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return jpaCustomerRepository.findAll().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findActiveCustomer() {
        return jpaCustomerRepository.findActiveCustomer().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCustomerStatus(Long id, boolean status) {
        jpaCustomerRepository.updateCustomerStatus(id, status);
    }


}
