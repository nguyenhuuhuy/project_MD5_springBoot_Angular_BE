package com.example.demo.service.author;

import com.example.demo.model.Author;
import com.example.demo.service.IGenderService;

import java.util.Optional;


public interface IAuthorService extends IGenderService<Author> {
    Optional<Author> findByName(String name);

    boolean existsByName(String name);
}
