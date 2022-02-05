package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void create(Id questionId, UserId userId, Comment comment) {
        boolean isCreated = commentRepository.create(questionId, userId, comment);
    }

    public void delete(Id commentId, Id questionId, UserId userId) {
        boolean isDeleted = commentRepository.delete(commentId, questionId, userId);
    }
}
