package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryArticleRepository implements ArticleRepository{

    private final Map<Integer, Article> articles = new ConcurrentHashMap<>();
    private final AtomicInteger index = new AtomicInteger();

    @Override
    public int save(Article article) {
        article.setId(index.incrementAndGet());
        articles.put(index.get(), article);
        return index.get();
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
