package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    private static Map<Long, Article> store = new HashMap<>();
    private static long sequence = 0L;

    public Article save(Article article) {
        article.setId(++sequence);
        store.put(article.getId(), article);
        return article;
    }

    public Article findById(Long id) {
        return store.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }
}
