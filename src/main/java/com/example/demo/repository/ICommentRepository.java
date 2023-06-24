package com.example.demo.repository;

import com.example.demo.model.Chapter;
import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
