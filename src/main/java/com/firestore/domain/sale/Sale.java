package com.firestore.domain.sale;

import com.firestore.domain.customer.Customer;
import com.firestore.domain.employee.Employee;

import java.sql.Timestamp;
import java.time.Instant;


public class Sale {

    private Long id;
    private String observation;
    private Double totalValue;
    private Long totalQuantity = 0L;
    private Timestamp date;
    private Employee employee;
    private Customer customer;

    public void onPrePersist() {
        this.date = Timestamp.from(Instant.now());

    }

    public Sale() {}

    public Sale(Customer customer, Timestamp date, Employee employee, Long id, String observation, Long totalQuantity, Double totalValue) {
        this.customer = customer;
        this.date = date;
        this.employee = employee;
        this.id = id;
        this.observation = observation;
        this.totalQuantity = totalQuantity;
        this.totalValue = totalValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
