package com.example.demo.repository;


import com.example.demo.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IStoryRepository extends JpaRepository<Story,Long> {
    Optional<Story> findByName(String name);
    boolean existsByName(String storyName);
    List<Story> findTop10ByOrderByTotalViewDesc();
    @Query("select s from Story s join s.categoryList c where c.id = :id")
    List<Story> findStoryByCategoryId(@Param("id")Long id);


}
