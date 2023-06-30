package com.example.demo.service.comment;

import com.example.demo.model.Comment;
import com.example.demo.service.IGenderService;

import java.util.List;


public interface ICommentService extends IGenderService<Comment> {
    List<Comment> getCommentByStoryId(Long id);

}
