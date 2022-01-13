package com.kakao.cafe.domain.article;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryArticleRepository implements ArticleRepository{

    private final Map<Integer, Article> articles = new ConcurrentHashMap<>();
    private final AtomicInteger index = new AtomicInteger();

    @Override
    public void save(Article article) {
        article.setId(index.incrementAndGet());
        articles.put(index.get(), article);
    }

    @Override
    public Article findById(int articleId) {
        return articles.get(articleId);
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articles.values());
    }
}
