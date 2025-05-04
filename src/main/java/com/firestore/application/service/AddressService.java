package com.firestore.application.service;

import com.firestore.application.usecases.AddressUseCases;
import com.firestore.domain.Address.Address;
import com.firestore.domain.Address.AddressRepository;
import com.firestore.domain.customer.Customer;
import com.firestore.domain.customer.CustomerRepository;
import com.firestore.domain.employee.Employee;
import com.firestore.domain.employee.EmployeeRepository;
import com.firestore.domain.supplier.Supplier;
import com.firestore.domain.supplier.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressUseCases {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;

    public AddressService(AddressRepository addressRepository,
                          CustomerRepository customerRepository,
                          EmployeeRepository employeeRepository,
                          SupplierRepository supplierRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Address getAddressFromEntity(String entityType, Long entityId) {
        switch (entityType.toUpperCase()) {
            case "EMPLOYEE": {
                Employee employee = employeeRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                return employee.getAddress();
            }
            case "CUSTOMER": {
                Customer customer = customerRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
                return customer.getAddress();
            }
            case "SUPPLIER": {
                Supplier supplier = supplierRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
                return supplier.getAddress();
            }
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }

    @Override
    public void saveAddressAndBindToEntity(Address address, String entityType, Long entityId) {
        Address savedAddress = addressRepository.save(address);

        switch (entityType.toUpperCase()) {
            case "EMPLOYEE": {
                Employee employee = employeeRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                employee.setAddress(savedAddress);
                employeeRepository.save(employee);
                break;
            }
            case "CUSTOMER": {
                Customer customer = customerRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
                customer.setAddress(savedAddress);
                customerRepository.save(customer);
                break;
            }
            case "SUPPLIER": {
                Supplier supplier = supplierRepository.findById(entityId)
                        .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
                supplier.setAddress(savedAddress);
                supplierRepository.save(supplier);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }
}
