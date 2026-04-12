package com.example.demo.exception.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("product not found.id = " + productId);
    }
}
