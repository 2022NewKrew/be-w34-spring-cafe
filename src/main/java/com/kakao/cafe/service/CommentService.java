package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository jdbcCommentRepositoryImpl) {
        this.commentRepository = jdbcCommentRepositoryImpl;
    }

    @Transactional
    public void createComment(Integer qnaIndex, String writer, String contents) {
        Comment comment = new Comment(qnaIndex, writer, contents);
        commentRepository.save(comment);
    }
}
