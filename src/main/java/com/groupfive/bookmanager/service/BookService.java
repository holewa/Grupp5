package com.groupfive.bookmanager.service;

import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public ResponseEntity<List<Book>> getAllBooks() {

        //separera felhantering(till egen klass) och själva metoden senare
        try {
            List<Book> books = new ArrayList<>();
            bookRepo.findAll().forEach(book -> books.add(book));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Optional<Book>> findBookByIsbn(String isbn) {
        Optional<Book> books = bookRepo.findByIsbn(isbn);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookByTitle(String title) {
        Optional<Book> books = bookRepo.findByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookByAuthor(String author) {
        Optional<Book> books = bookRepo.findByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookByGenre(String genre) {
        Optional<Book> books = bookRepo.findByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookByPublishedYear(Integer publishedYear) {
        Optional<Book> books = bookRepo.findByPublishYear(publishedYear);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookByRating(Integer rating) {
        Optional<Book> books = bookRepo.findByRating(rating);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findBookById(Long id) {
        Optional<Book> books = bookRepo.findById(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Book>> findLogic(String isbn, String title, String author, String genre, Integer publishedYear, Integer rating, Long id) {
        if (isbn != null) {
            return findBookByIsbn(isbn);
        }
        if (title != null) {
            return findBookByTitle(title);
        }
        if (author != null) {
            return findBookByAuthor(author);
        }
        if (genre != null) {
            return findBookByGenre(genre);
        }
        if (publishedYear != null) {
            return findBookByPublishedYear(publishedYear);
        }
        if (rating != null) {
            return findBookByRating(rating);
        }
        if (id != null) {
            return findBookById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Book> createBook(Book book) {
        Book newBook = bookRepo.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    public ResponseEntity<Book> updateBook(Book book, Long id) {
        Optional<Book> bookData = bookRepo.findById(id);
        if (bookData.isPresent()) {
            Book bookUpdate = bookData.get();
            if (book.getAuthor() != null) {
                bookUpdate.setAuthor(book.getAuthor());
            }
            if (book.getGenre() != null) {
                bookUpdate.setGenre(book.getGenre());
            }
            if (book.getIsbn() != null) {
                bookUpdate.setIsbn(book.getIsbn());
            }
            if (book.getTitle() != null) {
                bookUpdate.setTitle(book.getTitle());
            }
            if (book.getRating() != null) {
                bookUpdate.setRating(book.getRating());
            }
            if (book.getPublishYear() != null) {
                bookUpdate.setPublishYear(book.getPublishYear());
            }
            return new ResponseEntity<>(bookRepo.save(bookUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Book> deleteBookById(Long id) {
        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Book> deleteBookByIsbn(String isbn) {
        bookRepo.deleteByIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Book> deleteBookByTitle(String title) {
        bookRepo.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Book> deleteLogic(Long id, String isbn, String title) {
        if (id != null) {
            return deleteBookById(id);
        }
        if (isbn != null) {
            return deleteBookByIsbn(isbn);
        }
        if (title != null) {
            return deleteBookByTitle(title);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
