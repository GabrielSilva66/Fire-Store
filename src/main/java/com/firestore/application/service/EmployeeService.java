package com.firestore.application.service;


import com.firestore.domain.employee.Employee;
import com.firestore.adapters.outbound.repositories.JpaEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final JpaEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(JpaEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findActiveEmployees() {
        return employeeRepository.findActiveEmployee();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deactivateEmployee(Long id) {
        employeeRepository.updateEmployeeStatus(id, false);
    }

    public Employee saveOrUpdate(Employee employee) {
        if (employee.getId() != null) {
            Optional<Employee> existing = employeeRepository.findById(employee.getId());
            if (existing.isPresent()) {
                Employee toUpdate = existing.get();
                toUpdate.setName(employee.getName());
                toUpdate.setEmail(employee.getEmail());
                toUpdate.setRole(employee.getRole());
                return employeeRepository.save(toUpdate);
            }
        }
        return employeeRepository.save(employee);
    }
}
