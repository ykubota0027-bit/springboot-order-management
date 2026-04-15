package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/books/bookForm")
    public String iniBookForm() {
        return "books/bookForm";
    }

    @PostMapping("/books")
    public String createBookd() {

    }
}
