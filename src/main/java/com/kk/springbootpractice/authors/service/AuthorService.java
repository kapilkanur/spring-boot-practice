package com.kk.springbootpractice.authors.service;

import com.kk.springbootpractice.authors.dto.AuthorDTO;
import com.kk.springbootpractice.authors.entity.Author;
import com.kk.springbootpractice.authors.repository.AuthorRepository;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public boolean add(AuthorDTO authorDTO) {
        Author author = new Author();
        BeanUtils.copyProperties(authorDTO, author);
        try {
            authorRepository.save(author);
        } catch (DataAccessException dataAccessException) {
            log.error("Error while saving to the database : {}", dataAccessException.getMessage());
        }
        return true;
    }

    public AuthorDTO get(String name, String url) {
        Optional<Author> author = authorRepository.findByNameAndUrl(name, url);
        if (!author.isPresent()) {
            throw new NoResultException("No records for the Author : " + name);
        }
        AuthorDTO authorDTO = new AuthorDTO();
        BeanUtils.copyProperties(author.get(), authorDTO);
        return authorDTO;
    }
}
