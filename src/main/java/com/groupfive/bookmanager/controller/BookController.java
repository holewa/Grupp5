package com.groupfive.bookmanager.controller;

import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController()
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }
}
