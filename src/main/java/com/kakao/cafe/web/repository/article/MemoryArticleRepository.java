package com.kakao.cafe.web.repository.article;

import com.kakao.cafe.web.domain.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryArticleRepository implements ArticleRepository {

    private static List<Article> articles = new ArrayList<>();
    private static Long sequence = 0L;

    @Override
    public void save(Article article) {
        article.setId(++sequence);
        articles.add(article);
    }

    @Override
    public void update(Article updateArticle) {
        articles.stream()
                .filter(article -> updateArticle.getId().equals(article.getId()))
                .findFirst()
                .ifPresent(article -> {
                    article.setTitle(updateArticle.getTitle());
                    article.setContent(updateArticle.getContent());
                });
    }

    @Override
    public void delete(Long id) {
        articles.stream()
                .filter(article -> id.equals(article.getId()))
                .findFirst()
                .ifPresent(article -> articles.remove(article));

    }

    @Override
    public Optional<Article> findById(Long id) {
        return articles.stream()
                .filter(article -> id.equals(article.getId()))
                .findFirst();
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public void clearStore() {
        articles.clear();
    }
}
