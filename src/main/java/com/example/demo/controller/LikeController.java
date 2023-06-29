package com.example.demo.controller;

import com.example.demo.dto.request.LikeDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Comment;
import com.example.demo.model.Like;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.like.ILikeService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/likes")
@CrossOrigin(origins = "*")
public class LikeController {
    @Autowired
    private ILikeService likeService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<?> listLikes(){
        return new ResponseEntity<>(likeService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailLike(@PathVariable Long id){
        Optional<Like> like = likeService.findById(id);
        if (!like.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        return new ResponseEntity<>(like,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createLike(@RequestBody LikeDTO likeDTO) {
        Like like = new Like(likeDTO.getStory());
        User user = userDetailService.getCurrentUser();
        like.setUser(user);
        like.setStoryRelate(like.getStory());
        Boolean checkLike = likeService.existsByStoryRelateIdAndUserId(like.getStory().getId(),like.getUser().getId());
        if (checkLike){
            return new ResponseEntity<>(new ResponMessage("dame"),HttpStatus.OK);
        }
        likeService.save(like);
        return new ResponseEntity<>(new ResponMessage("like_success"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdminComment(@PathVariable Long id){
        String role = "";
        User user = userDetailService.getCurrentUser();
        role = userService.getUserRole(user);
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        if (!role.equals("ADMIN")){
            return new ResponseEntity<>(new ResponMessage("access_denied"),HttpStatus.OK);
        }

        Optional<Like> like = likeService.findById(id);
        if (!like.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        likeService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
}
