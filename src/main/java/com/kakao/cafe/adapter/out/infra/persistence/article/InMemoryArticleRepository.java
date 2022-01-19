package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryArticleRepository implements ArticleRepository {

    private static final int FIRST_ID = 1;

    private final Map<Integer, Article> repository;
    private final AtomicInteger atomicInt = new AtomicInteger(FIRST_ID);

    public InMemoryArticleRepository() {
        repository = new HashMap<>();
    }

    public void save(Article article) {
        article.setId(atomicInt.getAndIncrement());
        repository.put(article.getId(), article);
    }

    @Override
    public void update(Article article) {
        save(article);
    }

    @Override
    public void deleteById(int id) {
        repository.remove(id);
    }

    public List<Article> getAllArticleList() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Article> findById(int index) {
        return Optional.of(repository.get(index));
    }
}
