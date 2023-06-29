package com.example.demo.controller;

import com.example.demo.dto.request.ChangeNameChapterDTO;
import com.example.demo.dto.request.ChapterDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Chapter;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.chapter.IChapterService;
import com.example.demo.service.story.IStoryService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/chapter")
@CrossOrigin(origins = "*")
public class ChapterController {
    @Autowired
    private IChapterService chapterService;
    @Autowired
    private IStoryService storyService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> showListChapter() {
        return new ResponseEntity<>(chapterService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageListChapter(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(chapterService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailChapter(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (!chapter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Story story = chapter.get().getStory();
        story.setTotalView(story.getTotalView() + 1);
        storyService.saveViews(story);
        chapter.get().setChapterView(chapter.get().getChapterView() + 1L);
        chapterService.save(chapter.get());
        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailChapterById(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (!chapter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createChapter(@RequestBody ChapterDTO chapterDTO) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Chapter chapter = new Chapter(chapterDTO.getName(), chapterDTO.getStory());
        if (chapterService.existsByName(chapter.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
        }
        if (chapter.getStory() == null){
            return new ResponseEntity<>(new ResponMessage("not_found_story"),HttpStatus.OK);
        }
            chapterService.save(chapter);
            return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editChapter(@RequestBody Chapter chapter, @PathVariable Long id) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Chapter> chapter1 = chapterService.findById(id);
        if (!chapter.getName().equals(chapter1.get().getName())) {
            if (chapterService.existsByName(chapter.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (chapter.getName().equals(chapter1.get().getName())) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        chapter.setId(chapter1.get().getId());
        chapterService.save(chapter);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long id) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Chapter> chapter = chapterService.findById(id);
        if (!chapter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        chapterService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }

    @GetMapping("/storyByChapter/{id}")          //lay thong tin story de render ra chapterImage
    public ResponseEntity<?> storyFindByChapter(@PathVariable Long id) {
        Optional<Story> story = chapterService.findStoryByChapterId(id);
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    @PutMapping("/change-name/{id}")
    public ResponseEntity<?> changeNameChapter(@PathVariable Long id, @RequestBody ChangeNameChapterDTO changeNameChapterDTO) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Chapter> chapter = chapterService.findById(id);
        if (!chapter.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        chapter.get().setName(changeNameChapterDTO.getName());
        chapterService.save(chapter.get());
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }
}

