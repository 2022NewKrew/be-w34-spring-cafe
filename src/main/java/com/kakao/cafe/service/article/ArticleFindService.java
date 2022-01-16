package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ArticleFindService {

    private final ArticleRepository articleRepository;

    public ArticleFindService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article findById(int articleId) {
        return articleRepository.findById(articleId);
    }

    @Transactional
    public List<Article> findByAll() {
        return articleRepository.findAll();
    }
}
