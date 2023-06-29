package com.example.demo.service.like;

import com.example.demo.model.Like;
import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.repository.ILikeRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceIMPL implements ILikeService {
    @Autowired
    ILikeRepository likeRepository;


    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public Page<Like> findAll(Pageable pageable) {
        return likeRepository.findAll(pageable);
    }

    @Override
    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }


    @Override
    public boolean existsByStoryIdAndUserId(Long s_id, Long u_id) {
        return likeRepository.existsByStoryIdAndUserId(s_id,u_id);
    }

    @Override
    public List<Like> getLikeByStoryId(Long id) {
        return likeRepository.getLikeByStoryId(id);
    }
}
