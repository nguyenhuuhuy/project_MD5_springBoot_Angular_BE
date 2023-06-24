package com.example.demo.service.like;

import com.example.demo.model.Chapter;
import com.example.demo.model.Like;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.service.IGenderService;

import java.util.Optional;

public interface ILikeService extends IGenderService<Like> {
    boolean existsByStoryRelateIdAndUserId(Long s_id,Long u_id);

}
