package com.kakao.cafe.domain.repository.article;

import com.kakao.cafe.domain.entity.Article;

import java.util.*;

public class MemoryArticleRepository implements ArticleRepository {

    private static final Map<Long, Article> store = new Hashtable<>();
    private static long sequence = 0L;

    @Override
    public void save(Article article) {
        article.setId(++sequence);
        store.put(article.getId(), article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public long countRecords() {
        return store.size();
    }
}
