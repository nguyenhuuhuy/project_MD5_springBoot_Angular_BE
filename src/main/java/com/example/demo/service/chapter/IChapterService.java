package com.example.demo.service.chapter;

import com.example.demo.model.Chapter;
import com.example.demo.model.Story;
import com.example.demo.service.IGenderService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IChapterService extends IGenderService<Chapter> {
    boolean existsByName(String name);
    Optional<Story> findStoryByChapterId(@Param("id")Long id);
    List<Chapter> getChaptersByStoryId(Long id);


}
