package com.example.demo.exception.order;

public class OrderAlreadyCancelledException extends RuntimeException {
    public OrderAlreadyCancelledException(Long orderId) {
        super("orderAlreadyCancelled.orderId = " + orderId);
    }
}
