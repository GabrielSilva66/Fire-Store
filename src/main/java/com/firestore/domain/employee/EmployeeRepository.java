package com.firestore.domain.employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
    List<Employee> findAll();
    List<Employee> findActiveEmployee();

}
