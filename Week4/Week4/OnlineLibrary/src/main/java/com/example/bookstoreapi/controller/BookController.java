package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookMapper;
import com.example.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    // GET: /books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author) {
        List<Book> books = bookService.findByTitleAndAuthor(title, author);
        List<BookDTO> bookDTOs = books.stream()
                                      .map(bookMapper::bookToBookDTO)
                                      .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookListHeader");
        return new ResponseEntity<>(bookDTOs, headers, HttpStatus.OK);
    }

    // GET: /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> {
                    BookDTO bookDTO = bookMapper.bookToBookDTO(book);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "BookDetailsHeader");
                    return new ResponseEntity<>(bookDTO, headers, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: /books
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book createdBook = bookService.save(book);
        BookDTO createdBookDTO = bookMapper.bookToBookDTO(createdBook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/books/" + createdBookDTO.getId());
        return new ResponseEntity<>(createdBookDTO, headers, HttpStatus.CREATED);
    }

    // PUT: /books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.findById(id)
                .map(book -> {
                    book.setTitle(bookDTO.getTitle());
                    book.setAuthor(bookDTO.getAuthor());
                    book.setPrice(bookDTO.getPrice());
                    book.setIsbn(bookDTO.getIsbn());
                    Book updatedBook = bookService.save(book);
                    BookDTO updatedBookDTO = bookMapper.bookToBookDTO(updatedBook);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Custom-Header", "BookUpdatedHeader");
                    return new ResponseEntity<>(updatedBookDTO, headers, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> {
                    bookService.delete(book);
                    return ResponseEntity.noContent().build();
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
