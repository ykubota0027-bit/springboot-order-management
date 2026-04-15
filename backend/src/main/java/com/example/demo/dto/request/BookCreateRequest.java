package com.example.demo.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookCreateRequest {
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "author is required")
    private String author;
    @NotNull(message = "price is required")
    @Min(value = 1, message = "price must be greater than or equal to 1")
    private BigDecimal price;
    @NotNull(message = "publishedDate is required")
    private LocalDate publishedDate;

    public BookCreateRequest(String title, String author, BigDecimal price, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

}
