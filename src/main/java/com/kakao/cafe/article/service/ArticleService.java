package com.kakao.cafe.article.service;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findOneById(long id) {
        return articleRepository.findOneById(id);
    }

    public void update(long id, String title, String contents, String writer) {
        articleRepository.update(id, title, contents, writer);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }

}
