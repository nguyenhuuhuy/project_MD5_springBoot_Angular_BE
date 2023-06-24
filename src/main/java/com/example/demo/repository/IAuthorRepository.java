package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);  // tìm kiếm theo tên
    boolean existsByName(String name);    // kiểm tra tên có tồn tại trong db hay chưa
}
