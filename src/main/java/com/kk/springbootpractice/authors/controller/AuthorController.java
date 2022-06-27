package com.kk.springbootpractice.authors.controller;

import com.kk.springbootpractice.authors.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
