package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryArticleRepository implements ArticleRepository{

    private static final List<Article> store = Collections.synchronizedList(new ArrayList<Article>());

    private static final int FIRST_INDEX = 1;

    private final AtomicLong atomicLong = new AtomicLong(FIRST_INDEX);
    @Override
    public Article save(Article article) {
        article.setId(atomicLong.getAndIncrement());
        store.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return store;
    }

    @Override
    public Article findById(Long id) {
        return store.get(id.intValue()-1);
    }
}
