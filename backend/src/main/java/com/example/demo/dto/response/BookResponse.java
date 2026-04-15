package com.example.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.model.Book;

public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private LocalDate publishedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookResponse(Long id, String title, String author, BigDecimal price, LocalDate publishedDate,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BookResponse from(Book b) {
        return new BookResponse(
                b.getId(),
                b.getAuthor(),
                b.getAuthor(),
                b.getPrice(),
                b.getPublishedDate(),
                b.getCreatedAt(),
                b.getUpdatedAt());
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
