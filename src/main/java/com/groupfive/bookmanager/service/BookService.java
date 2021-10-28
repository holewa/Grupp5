package com.groupfive.bookmanager.service;

import com.groupfive.bookmanager.model.Book;
import com.groupfive.bookmanager.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    // Just nu ligger felhanteringen i vanliga try catch samt IF satser. Skulle vara cool om man kunde dra ut den till en egen klass, göra det lite mer snyggt.
    @Autowired
    BookRepo bookRepo;

    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findAll());
            if (books.isEmpty()) {
                return new ResponseEntity<>(books,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByIsbn(String isbn) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByIsbn(isbn));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByTitle(String title) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByTitle(title));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByAuthor(String author) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByAuthor(author));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByGenre(String genre) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByGenre(genre));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByPublishedYear(Integer publishedYear) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByPublishYear(publishedYear));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookByRating(Integer rating) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByRating(rating));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findBookById(Long id) {
        try {
            List<Book> books = new ArrayList<>(bookRepo.findByIdLong(id));
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> findLogic(String isbn, String title, String author, String genre, Integer publishedYear, Integer rating, Long id) {
        try {
            if (isbn != null) {
                return findBookByIsbn(isbn);
            } else if (title != null) {
                return findBookByTitle(title);
            } else if (author != null) {
                return findBookByAuthor(author);
            } else if (genre != null) {
                return findBookByGenre(genre);
            } else if (publishedYear != null) {
                return findBookByPublishedYear(publishedYear);
            } else if (rating != null) {
                return findBookByRating(rating);
            } else if (id != null) {
                return findBookById(id);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> createBook(Book book) {
        try {
            Book newBook = bookRepo.save(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> updateBook(Book book, Long id) {
        try {
            Optional<Book> bookData = bookRepo.findById(id);
            if (bookData.isPresent()) {
                Book bookUpdate = bookData.get();
                if (book.getAuthor() != null) {
                    bookUpdate.setAuthor(book.getAuthor());
                } if (book.getGenre() != null) {
                    bookUpdate.setGenre(book.getGenre());
                } if (book.getIsbn() != null) {
                    bookUpdate.setIsbn(book.getIsbn());
                } if (book.getTitle() != null) {
                    bookUpdate.setTitle(book.getTitle());
                } if (book.getImgUrl() != null) {
                    bookUpdate.setImgUrl(book.getImgUrl());
                } if (book.getRating() != null) {
                    bookUpdate.setRating(book.getRating());
                } if (book.getPublishYear() != null) {
                    bookUpdate.setPublishYear(book.getPublishYear());
                }
                return new ResponseEntity<>(bookRepo.save(bookUpdate), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> deleteBookById(Long id) {
        try {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // metod fungerar inte, felsök eller ta bort.
    public ResponseEntity<Book> deleteBookByIsbn(String isbn) {
        try {
            bookRepo.deleteByIsbn(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // metod fungerar inte, felsök eller ta bort.
    public ResponseEntity<Book> deleteBookByTitle(String title) {
        try {
            bookRepo.deleteByTitle(title);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> deleteLogic(Long id, String isbn, String title) {
        try {
            if (id != null) {
                return deleteBookById(id);
            }
            if (isbn != null) {
                // metod fungerar inte, felsök eller ta bort.
                return deleteBookByIsbn(isbn);
            }
            if (title != null) {
                // metod fungerar inte, felsök eller ta bort.
                return deleteBookByTitle(title);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
