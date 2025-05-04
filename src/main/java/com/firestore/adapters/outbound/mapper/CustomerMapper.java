package com.firestore.adapters.outbound.mapper;

import com.firestore.adapters.outbound.entities.JpaCustomerEntity;
import com.firestore.domain.customer.Customer;

public class  CustomerMapper {

    private CustomerMapper() {
        // Private constructor to prevent instantiation
    }

    public static Customer toDomain(JpaCustomerEntity customerEntity) {
        Customer customer = new Customer();

        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setEmail(customerEntity.getEmail());
        customer.setTelephone(customerEntity.getTelephone());
        customer.setAddress(AddressMapper.toDomain(customerEntity.getJpaAddress()));
        customer.setActive(customerEntity.isActive());
        return customer;

    }

    public static JpaCustomerEntity toEntity(Customer customer) {
        return  new JpaCustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getTelephone(),
                customer.isActive(),
                AddressMapper.toAddressEntity(customer.getAddress())
        );
    }
}
