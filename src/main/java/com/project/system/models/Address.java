package com.project.system.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Country is required") // Não aceita nulo ou vazio
    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @NotBlank(message = "Neighborhood is required")
    @Size(max = 100, message = "Neighborhood must not exceed 100 characters")
    private String neighborhood;

    @NotBlank(message = "CEP is required")
    @Size(max = 8, message = "CEP must have 8 characters")
    private String cep;

    @NotNull(message = "Number is required")
    @Min(value = 1, message = "Number must be greater than or equal to 1")
    private Integer number;

}
