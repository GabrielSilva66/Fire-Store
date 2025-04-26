package com.firestore.adapters.outbound.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "rl_item_order")
public class JpaItemOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be positive")
    private Double value;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private JpaOrderEntity jpaOrder;

    @ManyToOne
    @JoinColumn(name = "product_id" )
    private JpaProductEntity jpaProduct;
}
