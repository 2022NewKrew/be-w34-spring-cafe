package com.kakao.cafe.comment.application;

import com.kakao.cafe.comment.application.dto.CommentListResponse;
import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.comment.domain.CommentRepository;
import com.kakao.cafe.comment.infra.CommentJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentJdbcRepository commentJdbcRepository) {
        this.commentRepository = commentJdbcRepository;
    }

    public List<CommentListResponse> findAllByArticleId(int articleId) {
        log.info(this.getClass() + ": 댓글 목록");
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        log.info(comments.toString());
        return comments.stream().map(CommentListResponse::valueOf).collect(Collectors.toList());
    }

}
