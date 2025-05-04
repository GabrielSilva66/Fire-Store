package com.firestore.application.usecases;

import com.firestore.domain.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeUseCases {
    public List<Employee> findActiveEmployees();

    public Employee findById(Long id);

    public void deactivateEmployee(Long id);

    public Employee save(Employee employee);

    public Employee update(Employee employee);

}
