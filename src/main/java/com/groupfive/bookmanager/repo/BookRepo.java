package com.groupfive.bookmanager.repo;

import com.groupfive.bookmanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByAuthor(String author);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByGenre(String title);

    Optional<Book> findByPublishedYear(Integer publishedYear);

    Optional<Book> findByRating(Integer rating);

    Optional<Book> deleteByIsbn(String isbn);

    Optional<Book> deleteByTitle(String title);


}
