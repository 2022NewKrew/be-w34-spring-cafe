package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.exception.ArticleException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticlePostService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    public ArticlePostService(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    public Article postArticle(Article article) {
        validateNull(article);
        validateDuplicateArticle(article);
        validateExistWriterId(article.getWriterId().getUserId());
        return articleRepository.save(article);
    }

    private void validateNull(Article article) {
        if (article == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateDuplicateArticle(Article article) {
        boolean isDuplicated = articleRepository.findByArticleId(article.getArticleId()).isPresent();
        if (isDuplicated) {
            throw new ArticleException(ErrorCode.DUPLICATE_ARTICLE_ID);
        }
    }

    private void validateExistWriterId(String writerId) {
        userService.findUserByUserId(writerId);
    }
}
