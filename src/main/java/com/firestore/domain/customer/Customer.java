package com.firestore.domain.customer;


import com.firestore.domain.Address.Address;

public class Customer{
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private boolean isActive = true;
    private Address address;

    public Customer() {}

    public Customer(Address address, String telephone, boolean isActive, Long id, String email, String name) {
        this.address = address;
        this.telephone = telephone;
        this.isActive = isActive;
        this.id = id;
        this.email = email;
        this.name = name;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
