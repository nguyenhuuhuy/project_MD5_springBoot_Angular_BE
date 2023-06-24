package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.author.IAuthorService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/author")
@CrossOrigin(origins = "*")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserDetailService userDetailService;
    @GetMapping
    public ResponseEntity<?> showListAuthor() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (!author.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageListAuthor(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(authorService.findAll(pageable),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        if (authorService.existsByName(author.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_exist"), HttpStatus.OK);
        } else {
            authorService.save(author);
            return new ResponseEntity<>(new ResponMessage("create_author_success"), HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id,@RequestBody Author author){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        Optional<Author> author1 = authorService.findById(id);
        if (!author1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!author.getAuthorAlias().equals(author1.get().getAuthorAlias()) && author.getName().equals(author1.get().getName())){
            author.setId(author1.get().getId());
            authorService.save(author);
            return new ResponseEntity<>(new ResponMessage("update_alias_success"), HttpStatus.OK);
        }
        if (!author.getName().equals(author1.get().getName())) {
            if (authorService.existsByName(author.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (author.getName().equals(author1.get().getName()) && author.getAuthorAlias().equals(author1.get().getAuthorAlias())) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        author.setId(author1.get().getId());
        authorService.save(author);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        Optional<Author> author = authorService.findById(id);
        if (!author.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
}
