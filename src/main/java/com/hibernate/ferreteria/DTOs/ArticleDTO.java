package com.hibernate.ferreteria.DTOs;

// Solo se usa para transferir datos entre capas

public class ArticleDTO {
    private long id;
    private String name;
    private Double price;
    private Integer stock;
    private Integer supplierId; // ← agregar
    private Integer storeId;

    public ArticleDTO(long id, String name, Double price, Integer stock, Integer supplierId, Integer storeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.supplierId = supplierId;
        this.storeId = storeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
