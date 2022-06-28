package com.kk.springbootpractice.authors.controller;

import com.kk.springbootpractice.authors.dto.AuthorDTO;
import com.kk.springbootpractice.authors.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET, name = "Get author name by name and URL", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthor(@RequestParam(name = "name") String name, @RequestParam(name = "url") String url) {
        try {
            return new ResponseEntity<>(authorService.get(name, url), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "Get author by id", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthorById(@PathVariable @Positive(message = "Invalid id") int id) {
        try {
            return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(name = "Get authors", value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthors() {
        try {
            return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "Add author", value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        try {
            return new ResponseEntity<>(authorService.add(authorDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
