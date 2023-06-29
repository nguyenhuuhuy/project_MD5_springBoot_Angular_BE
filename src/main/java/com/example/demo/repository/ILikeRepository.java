package com.example.demo.repository;

import com.example.demo.model.Like;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ILikeRepository extends JpaRepository<Like,Long> {
    boolean existsByStoryIdAndUserId(Long s_id,Long u_id);
    List<Like> getLikeByStoryId(Long id);
}
