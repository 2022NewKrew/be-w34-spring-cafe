package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleMemoryRepositoryImpl implements ArticleRepository{
    private static AtomicLong idSequence = new AtomicLong();

    private final static HashMap<Long, Article> articleDB = new HashMap<>();

    public Article find(Long id) {
        return articleDB.get(id);
    }

    public ArrayList<Article> findAll() {
        return new ArrayList<>(articleDB.values());
    }

    public Long persist(ArticleCreateRequestDTO dto) {
        articleDB.put(idSequence.get(), new Article(idSequence.get(), dto.title, dto.authorId, LocalDateTime.now(), 0, dto.contents));
        return idSequence.getAndIncrement();
    }

    public void increaseHit(Long id) {
        Article article = articleDB.get(id);
        article.increaseHit();
        articleDB.put(id, article);
    }
}
