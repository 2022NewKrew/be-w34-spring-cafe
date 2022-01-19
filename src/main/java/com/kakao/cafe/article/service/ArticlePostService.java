package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import com.kakao.cafe.util.exception.ForbiddenUserException;
import org.springframework.stereotype.Service;

@Service
public class ArticlePostService {

    private final ArticleService articleService;
    private final UserService userService;

    public ArticlePostService(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    public Article postArticle(Article article) {
        validateNull(article);
        validateDuplicateArticle(article);
        validateExistWriterId(article.getWriterId());
        return articleService.save(article);
    }

    private void validateNull(Article article) {
        if (article == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateDuplicateArticle(Article article) {
        boolean isDuplicated = articleService.isDuplicated(article.getArticleId());
        if (isDuplicated) {
            throw new ArticleException(ErrorCode.DUPLICATE_ARTICLE_ID);
        }
    }

    private void validateExistWriterId(UserId writerId) {
        userService.findByUserId(writerId);
    }

    public void modify(Article article, UserId loginId) {
        validateLoginUser(article, loginId);
        articleService.update(article);
    }

    public void delete(Long articleId, UserId loginId) {
        Article article = articleService.findByArticleId(articleId);
        validateLoginUser(article, loginId);
        articleService.delete(articleId);
    }

    public Article findByArticleIdAndLoginId(Long articleId, UserId loginId) {
        Article article = articleService.findByArticleId(articleId);
        validateLoginUser(article, loginId);
        return article;
    }

    private void validateLoginUser(Article article, UserId loginId) {
        boolean isLoginUser = article.equalWriterId(loginId);
        if(!isLoginUser) {
            throw new ForbiddenUserException(ErrorCode.FORBIDDEN_USER);
        }
    }
}
