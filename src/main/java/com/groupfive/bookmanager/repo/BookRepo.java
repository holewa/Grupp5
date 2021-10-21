package com.groupfive.bookmanager.repo;

import com.groupfive.bookmanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(value = "SELECT c FROM Book c WHERE c.isbn LIKE %?1")
    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT c FROM Book c WHERE c.author LIKE %?1")
    Optional<Book> findByAuthor(String author);

    @Query("SELECT c FROM Book c WHERE c.title LIKE %?1")
    Optional<Book> findByTitle(String title);

    @Query("SELECT c FROM Book c WHERE c.genre LIKE %?1")
    Optional<Book> findByGenre(String title);

    @Query("SELECT c FROM Book c WHERE c.publishYear = ?1")
    Optional<Book> findByPublishYear(Integer publishedYear);

    @Query("SELECT c FROM Book c WHERE c.rating = ?1")
    Optional<Book> findByRating(Integer rating);

    @Query("DELETE FROM Book c  WHERE c.isbn LIKE %?1")
    Optional<Book> deleteByIsbn(String isbn);

    @Query("DELETE FROM Book c  WHERE c.title LIKE %?1")
    Optional<Book> deleteByTitle(String title);


}
