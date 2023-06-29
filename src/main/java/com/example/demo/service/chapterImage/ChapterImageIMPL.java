package com.example.demo.service.chapterImage;

import com.example.demo.model.ChapterImage;
import com.example.demo.repository.IChapterImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChapterImageIMPL implements IChapterImageService{

    @Autowired
    IChapterImageRepository chapterImageRepository;


    @Override
    public List<ChapterImage> findAll() {
        return chapterImageRepository.findAll();
    }

    @Override
    public void save(ChapterImage chapterImage) {
        chapterImageRepository.save(chapterImage);
    }

    @Override
    public Page<ChapterImage> findAll(Pageable pageable) {
        return chapterImageRepository.findAll(pageable);
    }

    @Override
    public Optional<ChapterImage> findById(Long id) {
        return chapterImageRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        chapterImageRepository.deleteById(id);
    }

    @Override
    public List<ChapterImage> getChapterImageByChapterId(Long id) {
        return chapterImageRepository.getChapterImageByChapterId(id);
    }
}
