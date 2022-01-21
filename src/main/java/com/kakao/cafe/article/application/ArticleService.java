package com.kakao.cafe.article.application;

import com.kakao.cafe.article.application.dto.ArticleListResponse;
import com.kakao.cafe.article.application.dto.ArticleSaveRequest;
import com.kakao.cafe.article.application.dto.ArticleShowResponse;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.infra.ArticleJdbcRepository;
import com.kakao.cafe.comment.application.dto.CommentListResponse;
import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.comment.domain.CommentRepository;
import com.kakao.cafe.comment.infra.CommentJdbcRepository;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.user.application.UserService;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    private final UserService userService;

    public ArticleService(
            ArticleJdbcRepository articleJdbcRepository,
            UserService userService,
            CommentJdbcRepository commentJdbcRepository
    ) {
        this.articleRepository = articleJdbcRepository;
        this.commentRepository = commentJdbcRepository;
        this.userService = userService;
    }

    @Transactional
    public void save(ArticleSaveRequest request) {
        log.info(this.getClass() + ": 게시글 작성");
        String authorId = request.writer;
        userService.validateUserExists(authorId);

        Article article = request.toArticle(authorId);
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleListResponse> findAll() {
        log.info(this.getClass() + ": 게시글 목록");
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(article -> {
                    int articleId = article.getId();
                    List<Comment> comments = commentRepository.findAllByArticleId(articleId);
                    return ArticleListResponse.valueOf(article, comments.size());
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleShowResponse findById(int articleId) {
        log.info(this.getClass() + ": 게시글 상세보기");
        Article article = articleRepository.findByIdOrNull(articleId);
        validateArticleExists(article, articleId);

        List<CommentListResponse> commentListResponses = commentRepository.findAllByArticleId(articleId).stream()
                .map(CommentListResponse::valueOf)
                .collect(Collectors.toList());

        return ArticleShowResponse.valueOf(article, commentListResponses);
    }

    @Transactional
    public void updateById(int articleId, ArticleSaveRequest request, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 게시글 수정");
        sessionedUser.validateSession(request.writer);
        Article article = articleRepository.findByIdOrNull(articleId);
        validateArticleExists(article, articleId);

        article.update(request.title, request.contents);
        articleRepository.update(article);
    }

    @Transactional
    public void deleteById(int articleId, SessionedUser sessionedUser) {
        log.info(this.getClass() + ": 게시글 삭제");
        Article article = articleRepository.findByIdOrNull(articleId);
        String authorId = article.getAuthorId();
        sessionedUser.validateSession(authorId);

        articleRepository.delete(article);
    }

    public void validateArticleExists(int articleId) throws EntityNotFoundException {
        Article article = articleRepository.findByIdOrNull(articleId);
        this.validateArticleExists(article, articleId);
    }

    private void validateArticleExists(Article article, int articleId) throws EntityNotFoundException {
        log.info(this.getClass() + ": 게시글 존재 여부 검증");
        if (article == null) {
            EntityNotFoundException.throwNotExistsByField(Article.class, "articleId", articleId);
        }
    }
}
