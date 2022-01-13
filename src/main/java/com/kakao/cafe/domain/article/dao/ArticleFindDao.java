package com.kakao.cafe.domain.article.dao;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exception.ArticleNotFoundException;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleFindDao {

    private final ArticleRepository articleRepository;

    public ArticleFindDao(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article findByid(final long id) {
        final Optional<Article> article = articleRepository.findById(id);
        article.orElseThrow(() -> new ArticleNotFoundException(id));
        return article.get();
    }

    public List<Article> findByUserId(final String userId) {
        List<Article> articles = articleRepository.findByUserId(userId);
        return articles;
    }

    public List<Article> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }
}
