package com.ecommerce.inventory_service.entity;

import jakarta.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private Long productId;

    private int quantity;

    public Stock(){}

    public Stock(Long productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getStockId() {
        return stockId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}