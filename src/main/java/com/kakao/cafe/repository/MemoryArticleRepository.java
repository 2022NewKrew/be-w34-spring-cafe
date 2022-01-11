package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryArticleRepository implements ArticleRepository{

    private static final Map<Long, Article> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        store.put(article.getId(), article);
        return article;
    }
}
