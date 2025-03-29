package com.project.system.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;



import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_product")
public class Product  implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Code bar is required")
    @Size(max = 100, message = "Code bar must not exceed 100 characters")
    @Column(name = "code_bar")
    private String codeBar;

    @NotBlank(message = "Unit of measure is required")
    @Size(max = 50, message = "Unit of measure must not exceed 50 characters")
    @Column(name = "unit_measure")
    private String unitMeasure;

    @NotNull(message = "Cost price is required")
    @PositiveOrZero(message = "Cost price must be zero or positive")
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @NotNull(message = "Sale price is required")
    @PositiveOrZero(message = "Sale price must be zero or positive")
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock must be zero or positive")
    private long stock;

    @Column(name = "picture_url", nullable = true)
    @Size(max = 255, message = "Picture URL must not exceed 255 characters")
    private String pictureUrl;

}
