package com.example.demo.service.comment;

import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceIMPL implements ICommentService {
    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void save(Comment comment) {
        User user = userDetailService.getCurrentUser();
        comment.setUser(user);
        commentRepository.save(comment);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentByStoryId(Long id) {
        return commentRepository.getCommentByStoryId(id);
    }
}
