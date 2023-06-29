package com.example.demo.repository;

import com.example.demo.model.Chapter;
import com.example.demo.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface IChapterRepository extends JpaRepository<Chapter, Long> {
    boolean existsByName(String name);
    @Query("select c.story from Chapter c where c.id = :id")
    Optional<Story> findStoryByChapterId(@Param("id")Long id);
//    @Query("select  from chapter c where story_id = :id")
    List<Chapter> getChaptersByStoryId(Long id);
}
