package com.kakao.cafe.article.application;

import com.kakao.cafe.article.application.dto.ArticleListResponse;
import com.kakao.cafe.article.application.dto.ArticleSaveRequest;
import com.kakao.cafe.article.application.dto.ArticleShowResponse;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.infra.ArticleJdbcRepository;
import com.kakao.cafe.comment.application.CommentService;
import com.kakao.cafe.comment.application.dto.CommentListResponse;
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
    private final UserService userService;
    private final CommentService commentService;

    public ArticleService(
            ArticleJdbcRepository articleJdbcRepository,
            UserService userService,
            CommentService commentService
    ) {
        this.articleRepository = articleJdbcRepository;
        this.userService = userService;
        this.commentService = commentService;
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

        return articles.stream().map(ArticleListResponse::valueOf).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleShowResponse findById(int articleId) {
        log.info(this.getClass() + ": 게시글 상세보기");
        Article article = articleRepository.findByIdOrNull(articleId);
        validateArticleExists(article, articleId);

        List<CommentListResponse> commentListResponses = commentService.findAllByArticleId(articleId);
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
