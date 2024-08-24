package com.example.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookstoreapi.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
