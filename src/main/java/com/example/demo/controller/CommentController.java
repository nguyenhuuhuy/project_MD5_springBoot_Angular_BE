package com.example.demo.controller;

import com.example.demo.dto.request.CommentDTO;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.comment.ICommentService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    IUserService userService;
    @GetMapping
    public ResponseEntity<?> listComment(){
        return new ResponseEntity<>(commentService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailComment(@PathVariable Long id){
        Optional<Comment> comment = commentService.findById(id);
        if (!comment.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
       Comment comment = new Comment(commentDTO.getStory(),commentDTO.getComment());
        commentService.save(comment);
        return new ResponseEntity<>(new ResponMessage("create_success"),HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?> pageListComment(Pageable pageable){
        return new ResponseEntity<>(commentService.findAll(pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        User user = userDetailService.getCurrentUser();
        if (user.getId() == null){
            return new ResponseEntity<>(new ResponMessage("no_user"),HttpStatus.OK);
        }
        Optional<Comment> comment = commentService.findById(id);
        if (!comment.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        if (!user.getId().equals(comment.get().getUser().getId())){
            return new ResponseEntity<>(new ResponMessage("not_accept"),HttpStatus.OK);
        }
            commentService.deleteById(id);
            return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
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

        Optional<Comment> comment = commentService.findById(id);
        if (!comment.isPresent()){
            return new ResponseEntity<>(new ResponMessage("not_found"),HttpStatus.OK);
        }
        commentService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"),HttpStatus.OK);
    }
}
