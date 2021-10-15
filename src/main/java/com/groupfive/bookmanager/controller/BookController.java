package com.groupfive.bookmanager.controller;

import com.groupfive.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8080")
@RestController("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/findall")
    public ResponseEntity getAllBooks() {
        return bookService.getAllBooks();
    }
}
