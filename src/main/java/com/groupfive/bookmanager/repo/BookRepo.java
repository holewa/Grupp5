package com.groupfive.bookmanager.repo;

import com.groupfive.bookmanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query("SELECT c FROM Book c WHERE c.isbn LIKE %?1")
    List<Book> findByIsbn(String isbn);

    @Query("SELECT c FROM Book c WHERE c.author LIKE %?1")
    List<Book> findByAuthor(String author);

    @Query("SELECT c FROM Book c WHERE c.title LIKE %?1")
    List<Book> findByTitle(String title);

    @Query("SELECT c FROM Book c WHERE c.genre LIKE %?1")
    List<Book> findByGenre(String title);

    @Query("SELECT c FROM Book c WHERE c.publishYear = ?1")
    List<Book> findByPublishYear(Integer publishedYear);

    @Query("SELECT c FROM Book c WHERE c.rating = ?1")
    List<Book> findByRating(Integer rating);

    @Query("SELECT c FROM Book c WHERE c.id = ?1")
    List<Book> findByIdLong(Long id);

    // metod fungerar inte, felsök eller ta bort.
    @Query("DELETE FROM Book c  WHERE c.isbn LIKE %?1")
    Optional<Book> deleteByIsbn(String isbn);

    // metod fungerar inte, felsök eller ta bort.
    @Query("DELETE FROM Book c  WHERE c.title LIKE %?1")
    Optional<Book> deleteByTitle(String title);


}
