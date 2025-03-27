package com.project.system.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "Observation must not exceed 255 characters")
    private String observation;

    @NotNull(message = "Total value is required")
    @Positive(message = "Total value must be positive")
    private Double totalValue = 0.00;

    @NotNull(message = "Total quantity is required")
    @Positive(message = "Total quantity must be positive")
    private Double totalQuantity = 0.00;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @PrePersist
    public void onPrePersist() {
        this.date = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
