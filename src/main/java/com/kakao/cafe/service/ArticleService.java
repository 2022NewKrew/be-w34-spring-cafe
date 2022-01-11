package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(
            @Qualifier("memoryArticleRepository") ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticleAll() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow();
    }

    public Long writeArticle(String writer, String title, String contents) {
        Article article = Article.of(writer, title, contents);
        articleRepository.insertArticle(article);
        return article.getId();
    }
}
