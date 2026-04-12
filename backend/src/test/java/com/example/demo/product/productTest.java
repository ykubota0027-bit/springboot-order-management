package com.example.demo.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Product;

public class productTest {

    @Test
    void decreaseStock_在庫が十分なら減算できる() {
        Product product = new Product("Keyboard", BigDecimal.valueOf(3000), 10);

        product.decreaseStock(3);

        assertEquals(7, product.getStock());
    }
}
