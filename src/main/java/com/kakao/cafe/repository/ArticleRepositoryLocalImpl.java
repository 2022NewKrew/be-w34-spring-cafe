package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepositoryLocalImpl implements ArticleRepository {
    private static final AtomicLong count = new AtomicLong(1);
    private final List<Article> articles = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Article article) {
        article.setId(count.getAndIncrement());
        article.setCreationTime(LocalDateTime.now());
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public Article findById(Long id) throws NotFoundException {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("해당 아이디의 게시물이 없습니다."));
    }

    @Override
    public void deleteById(Long id) {
        articles.removeIf(article -> article.getId().equals(id));
    }
}
