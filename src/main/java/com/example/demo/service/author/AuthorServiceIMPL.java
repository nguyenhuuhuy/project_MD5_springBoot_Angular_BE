package com.example.demo.service.author;

import com.example.demo.model.Author;
import com.example.demo.model.User;
import com.example.demo.repository.IAuthorRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceIMPL implements IAuthorService {
    @Autowired
    private IAuthorRepository authorRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    @Override
    public void save(Author author) {
        User user = userDetailService.getCurrentUser();
        author.setUser(user);
        authorRepository.save(author);
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return authorRepository.existsByName(name);
    }

    @Override
    public List<Author> findByNameContaining(String name) {
        return authorRepository.findByNameContaining(name);
    }

}
