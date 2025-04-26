package com.firestore.domain.order;

import com.firestore.domain.supplier.Supplier;
import com.firestore.domain.employee.Employee;

import java.sql.Timestamp;
import java.time.Instant;


public class Order {

    private Long id;
    private String observation;
    private Double totalValue = 0.00;
    private Long totalQuantity = 0L;
    private Timestamp date;
    private Employee employee;
    private Supplier supplier;


    public void onPrePersist() {
        this.date = Timestamp.from(Instant.now());

    }

    public Order() {}


    public Order(Timestamp date, Employee employee, Long id, String observation, Supplier supplier, Long totalQuantity, Double totalValue) {
        this.date = date;
        this.employee = employee;
        this.id = id;
        this.observation = observation;
        this.supplier = supplier;
        this.totalQuantity = totalQuantity;
        this.totalValue = totalValue;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
