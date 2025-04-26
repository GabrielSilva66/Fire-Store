package com.firestore.domain.product;


import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String codeBar;
    private String unitMeasure;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private long stock;
    private String pictureUrl;

    public Product() {}

    public Product(String codeBar, BigDecimal costPrice, Long id, String name, String pictureUrl, BigDecimal salePrice, long stock, String unitMeasure) {
        this.codeBar = codeBar;
        this.costPrice = costPrice;
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.salePrice = salePrice;
        this.stock = stock;
        this.unitMeasure = unitMeasure;
    }

    public String getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }
}
