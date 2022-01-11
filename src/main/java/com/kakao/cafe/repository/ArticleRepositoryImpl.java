package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public Optional<Article> findById(Long id) {
        Article article = null;
        try {
            article = articles.get(id.intValue());
        } catch (IndexOutOfBoundsException ignored) {}
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articles);
    }

    @Override
    public Long save(Article article) {
        articles.add(article);
        setIdInArticle(article);
        return article.getId();
    }

    private void setIdInArticle(Article article) {
        int id = articles.indexOf(article);
        article.setId((long) id);
    }
}
