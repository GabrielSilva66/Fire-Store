package com.firestore.application.service;


import com.firestore.application.usecases.EmployeeUseCases;
import com.firestore.domain.employee.Employee;
import com.firestore.domain.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeUseCases {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findActiveEmployees() {
        return employeeRepository.findActiveEmployee();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    public void deactivateEmployee(Long id) {
        employeeRepository.updateEmployeeStatus(id, false);
    }

    public Employee save(Employee employee) {
//        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
//            throw new IllegalArgumentException("Employee with ID already exists");
//        }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        Employee existing = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setRole(employee.getRole());

        return employeeRepository.save(existing);
    }

}
