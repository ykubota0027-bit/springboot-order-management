package com.example.demo.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateRequest {

    @NotBlank(message = "商品名は必須です")
    @Size(max = 15, message = "商品名は最大15文字以内で命名してください")
    private String productName;

    @NotNull(message = "値段は必須です")
    @Min(value = 0, message = "値段は0以上で入力してください")
    private BigDecimal price;

    @NotNull(message = "在庫数は必須です")
    @Min(value = 0, message = "在庫数は0以上で入力してください")
    private Integer stock;

    public ProductCreateRequest() {
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
