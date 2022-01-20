package com.kakao.cafe.comment.application;

import com.kakao.cafe.article.application.ArticleService;
import com.kakao.cafe.comment.application.dto.CommentListResponse;
import com.kakao.cafe.comment.application.dto.CommentSaveRequest;
import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.comment.domain.CommentRepository;
import com.kakao.cafe.comment.infra.CommentJdbcRepository;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public CommentService(ArticleService articleService, CommentJdbcRepository commentJdbcRepository) {
        this.articleService = articleService;
        this.commentRepository = commentJdbcRepository;
    }

    public List<CommentListResponse> findAllByArticleId(int articleId) {
        log.info(this.getClass() + ": 댓글 목록");
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments.stream().map(CommentListResponse::valueOf).collect(Collectors.toList());
    }

    @Transactional
    public void save(int articleId, SessionedUser sessionedUser, CommentSaveRequest commentSaveRequest) {
        log.info(this.getClass() + ": 댓글 작성");
        String authorId = sessionedUser.getUserId();
        articleService.validateArticleExists(articleId);
        Comment comment = commentSaveRequest.toComment(articleId, authorId);

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteById(String commentId, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 댓글 삭제");
        Comment comment = commentRepository.findByIdOrNull(commentId);
        String authorId = comment.getAuthorId();
        sessionedUser.validateSession(authorId);

        commentRepository.delete(comment);
    }
}
