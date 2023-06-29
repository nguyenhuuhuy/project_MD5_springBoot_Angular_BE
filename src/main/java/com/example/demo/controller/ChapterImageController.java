package com.example.demo.controller;

import com.example.demo.dto.request.ChapterImageDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.ChapterImage;
import com.example.demo.model.User;
import com.example.demo.repository.IChapterImageRepository;
import com.example.demo.security.userprincal.UserDetailService;
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
@RequestMapping("chapterImage")
@CrossOrigin(origins = "*")
public class ChapterImageController {
    @Autowired
    IChapterImageRepository chapterImageRepository;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    IUserService userService;
    @GetMapping
    public ResponseEntity<?> showListChapterImage() {
        return new ResponseEntity<>(chapterImageRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/getChapterByChapterImage/{id}")
    public ResponseEntity<?> showListChapterImage(@PathVariable Long id){
        return new ResponseEntity<>(chapterImageRepository.getChapterImageByChapterId(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailChapterImage(@PathVariable Long id) {
        Optional<ChapterImage> chapterImage = chapterImageRepository.findById(id);
        if (!chapterImage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chapterImage, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageChapterImage(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(chapterImageRepository.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createChapterImage(@RequestBody ChapterImageDTO chapterImageDTO) {
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        ChapterImage chapterImage = new ChapterImage(chapterImageDTO.getImage(),chapterImageDTO.getChapter());
        chapterImageRepository.save(chapterImage);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateChapterImage(@RequestBody ChapterImageDTO chapterImageDTO,@PathVariable Long id){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        Optional<ChapterImage> chapterImage = chapterImageRepository.findById(id);
        if (chapterImageDTO.getImage().equals(chapterImage.get().getImage())){
            return new ResponseEntity<>(new ResponMessage("no_change"),HttpStatus.OK);
        }
        chapterImage.get().setImage(chapterImageDTO.getImage());
        chapterImageRepository.save(chapterImage.get());
        return new ResponseEntity<>(new ResponMessage("update_success"),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapterImageById(@PathVariable Long id){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }
        Optional<ChapterImage> chapterImage = chapterImageRepository.findById(id);
        if (!chapterImage.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        chapterImageRepository.delete(chapterImage.get());
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
}
