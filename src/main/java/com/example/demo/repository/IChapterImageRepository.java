package com.example.demo.repository;

import com.example.demo.model.ChapterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IChapterImageRepository extends JpaRepository<ChapterImage,Long> {
    List<ChapterImage> getChapterImageByChapterId(Long id);
}
