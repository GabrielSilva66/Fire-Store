package com.firestore.adapters.outbound.entities;


import jakarta.persistence.*;
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
@Table(name = "rl_item_sale")
public class JpaItemSaleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private JpaSaleEntity jpaSale;

    @ManyToOne
    @JoinColumn(name = "product_id" )
    private JpaProductEntity jpaProduct;
}
