package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepositoryLocalImpl implements ArticleRepository {
    private static final AtomicLong count = new AtomicLong(1);
    private final List<Article> articles = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Article article) {
        article.setId(count.getAndIncrement());
        article.setCreationTime(new Date());
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }
}
