package com.example.demo.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderCreateRequest {
    @NotNull(message = "userId is required")
    private Long userId;
    @NotNull(message = "productId is required")
    private Long productId;
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be greater than or equal to 1")
    private Integer quantity;
    @NotNull(message = "price is required")
    private BigDecimal price;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(Long userId, Long productId, Integer quantity, BigDecimal price) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
