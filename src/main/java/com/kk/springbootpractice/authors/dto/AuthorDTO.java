package com.kk.springbootpractice.authors.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String url;

    @NotEmpty
    private String bio;
    
}
