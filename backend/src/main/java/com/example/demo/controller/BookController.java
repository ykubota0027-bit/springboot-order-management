package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.BookService;

@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books/bookForm")
    public String iniBookForm() {
        return "books/bookForm";
    }

    @PostMapping("/books/insert")
    public String createBookd(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam BigDecimal price,
            @RequestParam LocalDate publishedDate) {

        service.save(title, author, price, publishedDate);
        return "redirect:/books/bookForm";
    }
}
