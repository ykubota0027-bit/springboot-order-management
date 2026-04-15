package com.example.demo.dto.response;

import java.math.BigDecimal;

import com.example.demo.model.Order;

public class OrderResponse {
    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private String status;

    public OrderResponse(Long orderId, Long userId, Long productId, Integer quantity, BigDecimal price,
            BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public static OrderResponse from(Order o) {
        return new OrderResponse(
                o.getOrderId(),
                o.getUserId(),
                o.getProductId(),
                o.getQuantity(),
                o.getOrderedPrice(),
                o.getTotalAmount(),
                o.getStatus().name());
    }

    public Long getOrderId() {
        return orderId;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }
}
