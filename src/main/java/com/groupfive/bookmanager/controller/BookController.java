package com.groupfive.bookmanager.controller;

import com.groupfive.bookmanager.model.AuthRequest;
import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.service.BookService;
import com.groupfive.bookmanager.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController()
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassWord())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
        return jwtUtility.generateToken(authRequest.getUserName());
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Book> deleteBook(@RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String isbn,
                                           @RequestParam(required = false) String title) {
        return bookService.deleteLogic(id, isbn, title);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Book>> findBook(@RequestParam(required = false) String isbn,
                                               @RequestParam(required = false) String title,
                                               @RequestParam(required = false) String author,
                                               @RequestParam(required = false) String genre,
                                               @RequestParam(required = false) Integer publishedYear,
                                               @RequestParam(required = false) Integer rating,
                                               @RequestParam(required = false) Long id) {
        return bookService.findLogic(isbn, title, author, genre, publishedYear, rating, id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
        return bookService.updateBook(book, id);

    }


}
