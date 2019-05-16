package com.example.demo.controller;


import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Authors", description = "REST API for Authors", tags = {"Authors"})
@RequestMapping("/authors")
public class AuthorController {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Author getAuthorById(@PathVariable(name = "id") int id) {

        return authorRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void deleteAuthorById(@PathVariable(name = "id") int id) {
        authorRepository.deleteById(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void saveAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }

}
