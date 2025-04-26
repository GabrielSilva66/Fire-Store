package com.firestore.application.service;

import com.firestore.adapters.outbound.entities.JpaAddressEntity;
import com.firestore.adapters.outbound.entities.JpaCustomerEntity;
import com.firestore.adapters.outbound.entities.JpaEmployeeEntity;
import com.firestore.adapters.outbound.entities.JpaSupplierEntity;
import com.firestore.adapters.outbound.repositories.JpaAddressRepository;
import com.firestore.adapters.outbound.repositories.JpaCustomerRepository;
import com.firestore.adapters.outbound.repositories.JpaEmployeeRepository;
import com.firestore.adapters.outbound.repositories.JpaSupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final JpaAddressRepository addressRepository;
    private final JpaCustomerRepository customerRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaSupplierRepository supplierRepository;

    public AddressService(JpaAddressRepository addressRepository,
                          JpaCustomerRepository customerRepository,
                          JpaEmployeeRepository employeeRepository,
                          JpaSupplierRepository supplierRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.supplierRepository = supplierRepository;
    }

    public JpaAddressEntity getAddressFromEntity(String entityType, Long entityId) {
        return switch (entityType.toUpperCase()) {
            case "EMPLOYEE" -> employeeRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found"))
                    .getJpaAddress();
            case "CUSTOMER" -> customerRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"))
                    .getJpaAddress();
            case "SUPPLIER" -> supplierRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found"))
                    .getJpaAddress();
            default -> throw new IllegalArgumentException("Invalid entity type");
        };
    }

    public void saveAddressAndBindToEntity(JpaAddressEntity address, String entityType, Long entityId) {
        JpaAddressEntity savedAddress = addressRepository.save(address);

        switch (entityType.toUpperCase()) {
            case "EMPLOYEE" -> {
                JpaEmployeeEntity employee = employeeRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                employee.setJpaAddress(savedAddress);
                employeeRepository.save(employee);
            }
            case "CUSTOMER" -> {
                JpaCustomerEntity customer = customerRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
                customer.setJpaAddress(savedAddress);
                customerRepository.save(customer);
            }
            case "SUPPLIER" -> {
                JpaSupplierEntity supplier = supplierRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
                supplier.setJpaAddress(savedAddress);
                supplierRepository.save(supplier);
            }
            default -> throw new IllegalArgumentException("Invalid entity type");
        }
    }
}
