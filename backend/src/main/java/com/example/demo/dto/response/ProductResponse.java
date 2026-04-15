package com.example.demo.dto.response;

import java.math.BigDecimal;

public class ProductResponse {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Integer stock;

    public ProductResponse(Long id, String name, BigDecimal price, Integer stock) {
        this.productId = id;
        this.productName = name;
        this.price = price;
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
