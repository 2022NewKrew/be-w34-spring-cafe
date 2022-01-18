package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;

public class ArticleCreateService {
    private final ArticleRepository articleRepository;

    public ArticleCreateService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void create(Article article) {
        articleRepository.save(article);
    }
}
