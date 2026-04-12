package com.example.demo.exception.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("order not found.id = " + orderId);
    }
}
