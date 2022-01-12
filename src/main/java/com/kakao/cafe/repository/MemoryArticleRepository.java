package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

import java.util.*;

public class MemoryArticleRepository implements ArticleRepository {

    private static final Map<Long, Article> articleMap = new HashMap<>();
    private static Long idNumber = 0L;

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public void create(Article article) {
        articleMap.put(article.getId(), article);
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleMap.values());
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleMap.get(id));
    }
}
