package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal orderedPrice;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(Long userId, Long productId, Integer quantity, BigDecimal orderedPrice) {
        // NULLと数値チェック
        if (userId == null) {
            throw new IllegalArgumentException("UserId must not be null");
        }
        if (productId == null) {
            throw new IllegalArgumentException("productId must not be null");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        if (orderedPrice == null || orderedPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("orderedPrice must be greater than 0");
        }
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderedPrice = orderedPrice;
        this.totalAmount = orderedPrice.multiply(BigDecimal.valueOf(quantity));
        this.status = OrderStatus.CREATED;
    }

    public void cancell() {
        if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("order is already cancelled");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public boolean isCancelled() {
        return this.status == OrderStatus.CANCELLED;
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

    public BigDecimal getOrderedPrice() {
        return orderedPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
