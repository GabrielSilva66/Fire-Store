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
@Table(name = "tb_supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "CNPJ is required")
    @Size(max = 14, message = "CNPJ must not exceed 14 characters")
    private String cnpj;

    @NotBlank(message = "Email is required")
    @Size(max = 150, message = "Email must not exceed 150 characters") // Ajustado para consistência
    @Email(message = "Email must be a valid email address") // Validação específica para email
    private String email;

    @Size(max = 13, message = "Telephone number must not exceed 13 characters")
    @Pattern(regexp = "\\d{0,13}", message = "Telephone must contain only numbers") // Validação adicional
    private String telephone;

    private boolean is_active= true;


    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
