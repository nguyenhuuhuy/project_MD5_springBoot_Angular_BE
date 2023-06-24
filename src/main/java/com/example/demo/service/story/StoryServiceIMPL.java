package com.example.demo.service.story;

import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.repository.IStoryRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StoryServiceIMPL implements IStoryService {
    @Autowired
    private IStoryRepository storyRepository;
    @Autowired
    private UserDetailService userDetailService;


    @Override
    public List<Story> findAll() {
        return storyRepository.findAll();
    }

    @Override
    public Page<Story> findAll(Pageable pageable) {
        return storyRepository.findAll(pageable);
    }

    @Override
    public void save(Story story) {
        User user = userDetailService.getCurrentUser();
        story.setCreator(user);
        storyRepository.save(story);
    }

    @Override
    public Optional<Story> findById(Long id) {
        return storyRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        storyRepository.deleteById(id);
    }

    @Override
    public Optional<Story> findByName(String name) {
        return storyRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return storyRepository.existsByName(name);
    }

    @Override
    public void saveViews(Story story) {
        storyRepository.save(story);
    }

    @Override
    public List<Story> findTop10ByOrderByTotalView() {
        return storyRepository.findTop10ByOrderByTotalViewDesc();
    }

    @Override
    public Set<Story> storyList() {
        List<Story> storyList = storyRepository.findAll();
        Set<Story> storySet = new HashSet<>();
        int number;
        for (int i = 0; i < 10; i++) {
            number = (int) Math.floor(storyList.size() * Math.random());
            storySet.add(storyList.get(number));
            if (storySet.size() == 4){
                break;
            }
        }
        return storySet;
    }

    @Override
    public List<Story> findStoryByCategoryId(Long id) {
        return storyRepository.findStoryByCategoryId(id);
    }




}
