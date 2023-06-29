package com.example.demo.service.chapter;

import com.example.demo.model.Chapter;
import com.example.demo.model.Story;
import com.example.demo.repository.IChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChapterServiceIMPL implements IChapterService{
    @Autowired
    IChapterRepository chapterRepository;


    @Override
    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    @Override
    public void save(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    @Override
    public Page<Chapter> findAll(Pageable pageable) {
        return chapterRepository.findAll(pageable);
    }

    @Override
    public Optional<Chapter> findById(Long id) {
        return chapterRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return chapterRepository.existsByName(name);
    }

    @Override
    public Optional<Story> findStoryByChapterId(Long id) {
        return chapterRepository.findStoryByChapterId(id);
    }

    @Override
    public List<Chapter> getChaptersByStoryId(Long id) {
        return chapterRepository.getChaptersByStoryId(id);
    }


}
