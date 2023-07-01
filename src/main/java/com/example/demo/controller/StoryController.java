package com.example.demo.controller;

import com.example.demo.dto.request.StoryDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.story.IStoryService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/story")
@CrossOrigin(origins = "*")
public class StoryController {
    @Autowired
    private IStoryService storyService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> showListStory() {
        List<Story> storyList = storyService.findAll();
        Collections.reverse(storyList);
        return new ResponseEntity<>(storyList, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageListStory(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(storyService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/storyTop")
    public ResponseEntity<?> topListStory() {
        return new ResponseEntity<>(storyService.findTop10ByOrderByTotalView(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailStory(@PathVariable Long id) {
        Optional<Story> story = storyService.findById(id);
        if (!story.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<?> randomStory() {
        Set<Story> storySet = storyService.storyList();
        List<Story> storyList = new ArrayList<>(storySet);
        return new ResponseEntity<>(storyList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createStory(@RequestBody StoryDTO storyDTO) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Story story = new Story(storyDTO.getName(), storyDTO.getImage(), storyDTO.getContent(), storyDTO.getAuthor(), storyDTO.getCategoryList());
        if (storyService.existsByName(story.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
        } else {
            storyService.save(story);
            return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Story> story = storyService.findById(id);
        if (!story.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        storyService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editStory(@PathVariable Long id, @RequestBody StoryDTO storyDTO) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Story> story = storyService.findById(id);
        if (!story.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!storyDTO.getName().equals(story.get().getName())) {
            if (storyService.existsByName(storyDTO.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (storyDTO.getName().equals(story.get().getName()) && storyDTO.getImage().equals(story.get().getImage()) &&
                storyDTO.getCategoryList().equals(story.get().getCategoryList()) && storyDTO.getAuthor().getId().equals(story.get().getAuthor().getId()) &&
                storyDTO.getContent().equals(story.get().getContent())) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        story.get().setName(storyDTO.getName());
        story.get().setImage(storyDTO.getImage());
        story.get().setContent(storyDTO.getContent());
        story.get().setAuthor(storyDTO.getAuthor());
        story.get().setCategoryList(storyDTO.getCategoryList());
        storyService.save(story.get());
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @GetMapping("/storyByCategory/{id}")
    public ResponseEntity<?> storyFindByCategory(@PathVariable Long id) {
        List<Story> storyList = storyService.findStoryByCategoryId(id);
        return new ResponseEntity<>(storyList, HttpStatus.OK);
    }

    @GetMapping("block-story/{id}")
    public ResponseEntity<?> blockStory(@PathVariable Long id) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (!role.equals("ADMIN")) {
            return new ResponseEntity<>(new ResponMessage("access_denied"), HttpStatus.OK);
        }
        Optional<Story> story = storyService.findById(id);
        if (!story.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("no_found"), HttpStatus.OK);
        }
        if (story.get().getStatus()) {
            story.get().setStatus(false);
            storyService.saveViews(story.get());
            return new ResponseEntity<>(new ResponMessage("un_block_success"), HttpStatus.OK);
        }
        story.get().setStatus(true);
        storyService.saveViews(story.get());
        return new ResponseEntity<>(new ResponMessage("block_success"), HttpStatus.OK);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<?> getListSearchBandByName(@PathVariable String search) {
        List<Story> story = storyService.findByNameContaining(search);
        if (story.isEmpty()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(story, HttpStatus.OK);
    }
}
