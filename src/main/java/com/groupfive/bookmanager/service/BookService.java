package com.groupfive.bookmanager.service;

import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public ResponseEntity<List<Book>> getAllBooks() {
        //separera felhantering(till egen klass) och själva metoden senare
        try {
            List<Book> books = new ArrayList<Book>();
            bookRepo.findAll().forEach(book -> books.add((Book) book));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
