package com.kakao.cafe.article.service;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.repository.MemoryArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private ArticleRepository articleRepository = new MemoryArticleRepository();

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

}
