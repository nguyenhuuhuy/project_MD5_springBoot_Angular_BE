package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.IGenderService;

import java.util.List;
import java.util.Optional;


public interface ICategoryService extends IGenderService<Category> {
    Optional<Category> findByName(String name);

    boolean existsByName(String name);
    List<Category> findByNameContaining(String name);

}
