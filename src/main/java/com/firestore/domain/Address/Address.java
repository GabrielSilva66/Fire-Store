package com.firestore.domain.Address;

public class Address {

    private Long id;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String cep;
    private Integer number;

    public Address(){}

    public Address(String cep, String city, Long id, String country, String neighborhood, Integer number, String state) {
        this.cep = cep;
        this.city = city;
        this.id = id;
        this.country = country;
        this.neighborhood = neighborhood;
        this.number = number;
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
