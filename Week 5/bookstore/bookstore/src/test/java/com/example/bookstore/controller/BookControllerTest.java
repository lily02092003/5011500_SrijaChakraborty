package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnBooks() throws Exception {
        // Mock data
        Book book = new Book();
        book.setTitle("Some Book");
        book.setAuthor("Some Author");
        book.setPrice(19.99);

        // Mock repository behavior
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        // Perform the GET request and verify the response
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Some Book"))
                .andExpect(jsonPath("$[0].author").value("Some Author"))
                .andExpect(jsonPath("$[0].price").value(19.99));
    }
    @Test
    public void shouldReturnBookById() throws Exception {
        Book book = new Book();
        book.setTitle("Some Book");
        book.setAuthor("Some Author");
        book.setPrice(19.99);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Some Book"))
                .andExpect(jsonPath("$.author").value("Some Author"))
                .andExpect(jsonPath("$.price").value(19.99));
    }

}
