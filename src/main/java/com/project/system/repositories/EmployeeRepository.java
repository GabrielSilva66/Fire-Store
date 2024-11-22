package com.project.system.repositories;

import com.project.system.models.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM tb_employee WHERE is_active = true", nativeQuery = true)
    List<Employee> findActiveEmployee();


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_employee SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateEmployeeStatus(@Param("id") Long id, @Param("status") boolean status);

}
