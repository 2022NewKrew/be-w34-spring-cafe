package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Comment;
import com.kakao.cafe.article.domain.CommentRepository;
import com.kakao.cafe.article.dto.SingleComment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<SingleComment> getAllComments(Long articleId) {
        return commentRepository.findAllByArticleId(articleId);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
