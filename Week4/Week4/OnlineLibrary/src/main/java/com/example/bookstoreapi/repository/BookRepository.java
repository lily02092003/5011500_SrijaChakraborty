package com.example.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.bookstoreapi.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE (:title IS NULL OR b.title LIKE %:title%) AND (:author IS NULL OR b.author LIKE %:author%)")
    List<Book> findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);

    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorContaining(String author);
    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);

}
