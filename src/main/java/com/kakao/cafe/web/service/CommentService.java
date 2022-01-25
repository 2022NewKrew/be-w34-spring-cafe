package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.Comment;
import com.kakao.cafe.web.exception.NotFoundException;
import com.kakao.cafe.web.repository.comment.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void write(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 댓글입니다."));
        commentRepository.delete(id);
    }

    public List<Comment> findComments(Long articleId) {
        return commentRepository.findAllByArticleId(articleId);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 댓글입니다."));
    }


}
