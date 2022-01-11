package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryArticleRepository implements ArticleRepository{

    private static final Map<Long, Article> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        store.put(article.getId(), article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Article findById(Long id) {
        return store.get(id);
    }
}
