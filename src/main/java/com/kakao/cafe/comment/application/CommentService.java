package com.kakao.cafe.comment.application;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.comment.application.dto.CommentListResponse;
import com.kakao.cafe.comment.application.dto.CommentSaveRequest;
import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.comment.domain.CommentRepository;
import com.kakao.cafe.comment.infra.CommentJdbcRepository;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentService(ArticleRepository articleRepository, CommentJdbcRepository commentJdbcRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentJdbcRepository;
    }

    public List<CommentListResponse> findAllByArticleId(int articleId) {
        log.info(this.getClass() + ": 댓글 목록");
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments.stream().map(CommentListResponse::valueOf).collect(Collectors.toList());
    }

    public void save(int articleId, SessionedUser sessionedUser, CommentSaveRequest commentSaveRequest) {
        String authorId = sessionedUser.getUserId();
        validateArticleExists(articleId);
        Comment comment = commentSaveRequest.toComment(articleId, authorId);

        commentRepository.save(comment);
    }

    private void validateArticleExists(int articleId) throws EntityNotFoundException {
        Article article = articleRepository.findByIdOrNull(articleId);
        if (article == null) {
            EntityNotFoundException.throwNotExistsByField(Article.class, "articleId", articleId);
        }
    }
}
