package com.kk.springbootpractice.authors.service;

import com.google.common.collect.Lists;
import com.kk.springbootpractice.authors.dto.AuthorDTO;
import com.kk.springbootpractice.authors.entity.Author;
import com.kk.springbootpractice.authors.repository.AuthorRepository;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = Lists.newArrayList(authorRepository.findAll());
        if (authors.isEmpty()) {
            throw new NoResultException("No authors found");
        }
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        authors.forEach(author -> {
            AuthorDTO authorDTO = new AuthorDTO();
            BeanUtils.copyProperties(author, authorDTO);
            authorsDTO.add(authorDTO);
        });
        return authorsDTO;
    }
}
