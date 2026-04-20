package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.request.BookCreateRequest;
import com.example.demo.dto.response.BookResponse;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;

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

    @GetMapping("/books/bookList")
    public String showBookList(Model model) {
        List<BookResponse> books = service.findAll();
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @PostMapping("/books/insert")
    public String createBook(@Valid @ModelAttribute("book") BookCreateRequest r, BindingResult result) {

        service.save(r.getTitle(), r.getAuthor(), r.getPrice(), r.getPublishedDate());
        return "redirect:/books/bookForm";
    }
}
