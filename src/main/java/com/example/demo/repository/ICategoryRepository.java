package com.example.demo.repository;

import com.example.demo.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name); // tìm kiếm category theo tên
    boolean existsByName(String name);  // kiểm tra xem category đã tồn tại trong đb hay chưa
    List<Category> findByNameContaining(String name);
}
