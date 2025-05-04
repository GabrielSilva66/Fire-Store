package com.firestore.domain.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
    List<Employee> findAll();
    List<Employee> findActiveEmployee();
    void updateEmployeeStatus(Long id, boolean status);
    boolean existsById(Long id);



}
