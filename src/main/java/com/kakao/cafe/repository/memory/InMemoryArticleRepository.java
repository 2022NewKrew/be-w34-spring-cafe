package com.kakao.cafe.repository.memory;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryArticleRepository implements ArticleRepository {
    private static final Map<Long, Article> repository = new HashMap<>();
    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void saveArticle(Article article) {
        article.setId(count.getAndIncrement());
        repository.put(article.getArticleId(), article);
    }

    @Override
    public Optional<Article> findArticleById(Long articleId) {
        return Optional.ofNullable(repository.get(articleId));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(repository.values());
    }
}
