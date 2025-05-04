package com.firestore.adapters.outbound.repositories.imp;

import com.firestore.adapters.outbound.entities.JpaEmployeeEntity;
import com.firestore.adapters.outbound.mapper.EmployeeMapper;
import com.firestore.adapters.outbound.repositories.JpaEmployeeRepository;
import com.firestore.domain.employee.Employee;
import com.firestore.domain.employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {


    private final JpaEmployeeRepository jpaEmployeeRepository;

    public EmployeeRepositoryImp(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Optional<JpaEmployeeEntity> entity = this.jpaEmployeeRepository.findById(id);
        return entity.map(EmployeeMapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        JpaEmployeeEntity entity = EmployeeMapper.toEntity(employee);
        JpaEmployeeEntity saved = jpaEmployeeRepository.save(entity);
        return EmployeeMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll().stream()
                .map(EmployeeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findActiveEmployee() {
        return jpaEmployeeRepository.findActiveEmployee().stream()
                .map(EmployeeMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Transactional
    public void updateEmployeeStatus(Long id, boolean status) {
        jpaEmployeeRepository.updateEmployeeStatus(id, status);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaEmployeeRepository.existsById(id);
    }
}
