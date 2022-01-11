package com.kakao.cafe.article.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> table;
    private final AtomicLong pk;

    public MemoryArticleRepository() {
        table = new HashMap<>();
        pk = new AtomicLong();
    }

    @Override
    public Article save(Article article) {
        article.setId(nextId());
        table.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(table.getOrDefault(id, null));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(table.values());
    }

    private Long nextId() {
        return pk.incrementAndGet();
    }
}
