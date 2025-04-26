package com.firestore.adapters.outbound.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_order")
public class JpaOrderEntity implements Serializable {

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
    private Long totalQuantity = 0L;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "dt_created_at")
    private Timestamp date;

    @PrePersist
    public void onPrePersist() {
        this.date = Timestamp.from(Instant.now());

    }


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private JpaEmployeeEntity jpaEmployee;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private JpaSupplierEntity jpaSupplier;
}
