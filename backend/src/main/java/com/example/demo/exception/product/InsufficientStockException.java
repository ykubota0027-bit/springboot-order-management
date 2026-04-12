package com.example.demo.exception.product;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId) {
        super("insufficientStock.productId = " + productId);
    }
}
