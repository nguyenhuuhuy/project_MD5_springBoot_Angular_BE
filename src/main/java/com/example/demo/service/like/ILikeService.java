package com.example.demo.service.like;

import com.example.demo.model.Chapter;
import com.example.demo.model.Like;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.service.IGenderService;

import java.util.List;
import java.util.Optional;

public interface ILikeService extends IGenderService<Like> {
    boolean existsByStoryIdAndUserId(Long s_id,Long u_id);
    List<Like> getLikeByStoryId(Long id);

}
