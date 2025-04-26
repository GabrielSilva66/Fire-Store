package com.firestore.domain.supplier;

import com.firestore.domain.Address.Address;

public class Supplier {

    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String telephone;
    private boolean isActive = true;
    private Address address;

    public Supplier() {}

    public Supplier(Address address, String cnpj, String email, Long id, boolean isActive, String name, String telephone) {
        this.address = address;
        this.cnpj = cnpj;
        this.email = email;
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
