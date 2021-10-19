package com.groupfive.bookmanager.controller;

import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/createbook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable ("id") Long id){
        return bookService.deleteBookById(id);
    }
    @DeleteMapping("/deletebyisbn/{isbn}")
    public ResponseEntity<Book> deleteBookByIsbn(@PathVariable ("isbn") String isbn ){
        return bookService.deleteBookByIsbn(isbn);
    }
    @DeleteMapping("/deletebytitle/{title}")
    public ResponseEntity<Book> deleteBookBytitle(@PathVariable ("title") String title ){
        return bookService.deleteBookByTitle(title);
    }
    @GetMapping("/findbookbyauthor/{author}")
    public ResponseEntity<Optional<Book>> findBookByAuthor(@PathVariable ("author") String author ){
        return bookService.findBookByAuthor(author);
    }
    @GetMapping("/findbookbygenre/{genre}")
    public ResponseEntity<Optional<Book>> findBookByGenre(@PathVariable ("genre") String genre ){
        return bookService.findBookByGenre(genre);
    }
    @GetMapping("/findbookbyid/{id}")
    public ResponseEntity<Optional<Book>> findBookById(@PathVariable ("id") Long id ){
        return bookService.findBookById(id);
    }
    @GetMapping("/findbookbyisbn/{isbn}")
    public ResponseEntity<Optional<Book>> findBookByIsbn(@PathVariable ("isbn") String isbn ){
        return bookService.findBookByIsbn(isbn);
    }
    @GetMapping("/findbookbypublishedyear/{year}")
    public ResponseEntity<Optional<Book>> findBookByPublishedYear(@PathVariable ("year") Integer publishedYear ){
        return bookService.findBookByPublishedYear(publishedYear);
    }
    @GetMapping("/findbookbyrating/{rating}")
    public ResponseEntity<Optional<Book>> findBookByRating(@PathVariable ("rating") Integer rating ){
        return bookService.findBookByRating(rating);
    }
    @GetMapping("/findbookbytitle/{title}")
    public ResponseEntity<Optional<Book>> findBookByTitle(@PathVariable ("title") String title ){
        return bookService.findBookByTitle(title);
    }
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") Long id ){
        return bookService.updateBook(book, id);

    }


}
