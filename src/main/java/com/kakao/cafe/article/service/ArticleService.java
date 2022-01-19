package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.ArticleRegistrationDto;
import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.repo.ArticleRepository;
import com.kakao.cafe.user.model.User;
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

    public void create(ArticleRegistrationDto dto, User author) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(author);
        create(article);
    }

    public Article fetch(Long id) {
        return repository.fetch(id);
    }

    public List<Article> fetchAll() {
        return repository.fetchAll();
    }
}
