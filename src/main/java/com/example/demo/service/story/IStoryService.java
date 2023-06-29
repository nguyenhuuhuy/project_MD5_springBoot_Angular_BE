package com.example.demo.service.story;

import com.example.demo.model.Story;
import com.example.demo.service.IGenderService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IStoryService extends IGenderService<Story> {
    Optional<Story> findByName(String name);
    boolean existsByName(String name);
    void saveViews(Story story);

    List<Story> findTop10ByOrderByTotalView();

    Set<Story> storyList();
    List<Story> findStoryByCategoryId(@Param("id")Long id);
    List<Story> findByNameContaining(String name);


}
