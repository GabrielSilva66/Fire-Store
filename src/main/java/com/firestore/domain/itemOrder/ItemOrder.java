package com.firestore.domain.itemOrder;


import com.firestore.domain.order.Order;
import com.firestore.domain.product.Product;

public class ItemOrder {

    private Long id;
    private Double value;
    private Integer quantity;
    private Order order;
    private Product product;

    public ItemOrder() {}

    public ItemOrder(Long id, Order order, Product product, Integer quantity, Double value) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
