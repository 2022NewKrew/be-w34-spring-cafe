package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.request.ArticleRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.dto.response.ArticleResponse;
import com.kakao.cafe.article.dto.response.ArticleUpdateResponse;
import com.kakao.cafe.article.dto.response.ArticlesResponse;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.common.exception.AuthException;
import com.kakao.cafe.common.exception.EntityNotFoundException;
import com.kakao.cafe.common.exception.ErrorCode;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticleResponse save(Long userId, ArticleRequest articleRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));
        Article article = articleRequest.toArticle(user);
        return ArticleResponse.of(articleRepository.save(article));
    }

    public ArticlesResponse findAll() {
        return ArticlesResponse.of(articleRepository.findAll());
    }

    public ArticleDetailResponse findById(Long id, Long userId) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        article.incrementViewNum();
        articleRepository.updateViewNum(id);
        return ArticleDetailResponse.of(article, userId);
    }

    public ArticleUpdateResponse findUpdateArticleById(Long id, Long userId) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        if(!article.validateAuth(userId)){
            throw new AuthException(ErrorCode.AUTHORIZATION_ERROR);
        }
        return ArticleUpdateResponse.of(article);
    }

    public void update(Long id, Long userId, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        if(!article.validateAuth(userId)){
            throw new AuthException(ErrorCode.AUTHORIZATION_ERROR);
        }
        articleRepository.update(articleRequest.toArticle(id));
    }

    public void delete(Long id, Long userId) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        if(!article.validateAuth(userId)){
            throw new AuthException(ErrorCode.AUTHORIZATION_ERROR);
        }
        articleRepository.delete(id);
    }
}
