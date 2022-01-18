package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class MemoryArticleRepository implements ArticleRepository{

    private static final List<Article> store = Collections.synchronizedList(new ArrayList<Article>());

    private static final int FIRST_INDEX = 1;

    private final AtomicLong atomicLong = new AtomicLong(FIRST_INDEX);
    @Override
    public Long save(Article article) {
        article.setId(atomicLong.getAndIncrement());
        store.add(article);
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return store;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(store.get(id.intValue() - 1));
    }

    @Override
    public void updateArticle(Long id, Article updateArticle) {

    }

    @Override
    public void deleteArticle(Long id) {

    }
}
