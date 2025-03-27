package com.project.system.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @Size(max = 255, message = "Observation must not exceed 255 characters")
    private String observation;

    @NotNull(message = "Total value is required")
    @Positive(message = "Total value must be positive")
    private Double totalValue;

    @NotNull(message = "Total quantity is required")
    @Positive(message = "Total quantity must be positive")
    private Double totalQuantity;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @PrePersist
    public void onPrePersist() {
        this.date = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")  // Adicionando a coluna de relacionamento
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Adicionando a coluna de relacionamento
    private Customer customer;

}
