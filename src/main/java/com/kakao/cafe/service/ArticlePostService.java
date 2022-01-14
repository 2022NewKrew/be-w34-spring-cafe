package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlePostService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    public Article postArticle(Article article) {
        validateNull(article);
        validateDuplicateArticle(article);
        validateExistWriterId(article.getWriterId());
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
