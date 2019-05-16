package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "Books", description = "REST API for Books", tags = {"Books"})
@RequestMapping("/books")
public class BookController {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Book getBookById(@PathVariable(name = "id") int id) {

        return bookRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void deleteBookById(@PathVariable(name = "id") int id) {

        bookRepository.deleteById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void saveBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @GetMapping("/byAuthor/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Book> getBooksByAuthor(@PathVariable(name = "id") int id) {
        return bookRepository.findAllByAuthor(authorRepository.getOne(id));
    }


}
