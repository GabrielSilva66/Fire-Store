package com.firestore.domain.employee;

import com.firestore.domain.Address.Address;

public class Employee {
    private Long id;
    private String role;
    private String name;
    private String email;
    private String telephone;
    private boolean isActive = true;
    private Address address;

    public Employee() {}

    public Employee(Address address, String email, Long id, boolean isActive, String name, String role, String telephone) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.role = role;
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
