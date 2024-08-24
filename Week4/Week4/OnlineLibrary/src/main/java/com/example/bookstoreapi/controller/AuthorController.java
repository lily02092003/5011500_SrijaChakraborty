package com.example.bookstoreapi.controller;

import org.springframework.web.bind.annotation.*;
import com.example.bookstoreapi.model.Author;
import com.example.bookstoreapi.repository.AuthorRepository;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }
}
