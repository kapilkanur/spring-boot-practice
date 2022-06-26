package com.kk.springbootpractice.authors.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "author", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "url"}))
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String url;
    private String name;
    private String bio;

}
