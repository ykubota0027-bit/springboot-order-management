package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Integer stock;

    public Product() {
    }

    public Product(String name, BigDecimal price, Integer stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price must be 0 or greater");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("stock must be 0 or greater");
        }
        this.productName = name;
        this.price = price;
        this.stock = stock;
    }

    public void increaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("増加数は1以上を入力してください");
        }
        this.stock += amount;
    }

    public void decreaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("減少数は1以上を入力してください");
        }
        if (this.stock < amount) {
            throw new IllegalArgumentException("在庫が不足しています");
        }
        this.stock -= amount;
    }

    public void changePrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("値段は0以上を設定してください");
        }
        this.price = new BigDecimal(price);
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
