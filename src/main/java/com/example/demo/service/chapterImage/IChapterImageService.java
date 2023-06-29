package com.example.demo.service.chapterImage;

import com.example.demo.model.ChapterImage;
import com.example.demo.service.IGenderService;

import java.util.List;
import java.util.Optional;

public interface IChapterImageService extends IGenderService<ChapterImage> {
    List<ChapterImage> getChapterImageByChapterId(Long id);

}
