package com.firestore.domain.itemSale;

import com.firestore.domain.product.Product;
import com.firestore.domain.sale.Sale;

public class ItemSale {
    private Long id;
    private Double value;
    private Integer quantity;
    private Sale sale;
    private Product product;

    public ItemSale() {}

    public ItemSale(Long id, Product product, Integer quantity, Sale sale, Double value) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.sale = sale;
        this.value = value;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
