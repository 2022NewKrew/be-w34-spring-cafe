package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.repo.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public void create(@Valid Article article) {
        repository.save(article);
    }

    public Article fetch(Long id) {
        return repository.fetch(id);
    }

    public List<Article> fetchAll() {
        return repository.fetchAll();
    }
}
