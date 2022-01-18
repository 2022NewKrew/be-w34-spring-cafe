package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleMemoryRepositoryImpl implements ArticleRepository {
    private final static AtomicLong idSequence = new AtomicLong();
    private final static HashMap<Long, Article> articleDB = new HashMap<>();

    @Override
    public Long persist(Article article) {
        articleDB.put(idSequence.get(), makeArticle(idSequence.get(), article, LocalDateTime.now()));
        return idSequence.getAndIncrement();
    }

    @Override
    public Optional<Article> find(Long id) {
        return Optional.ofNullable(articleDB.get(id));
    }

    @Override
    public ArrayList<Article> findAll() {
        return new ArrayList<Article>(articleDB.values());
    }

    @Override
    public void increaseHit(Long id) {
        Article article = articleDB.get(id);
        article.increaseHit();
        articleDB.put(id, article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDB.put(article.getId(), article);
    }

    private Article makeArticle(Long id, Article article, LocalDateTime time) {
        return Article.builder()
                .id(id)
                .title(article.getTitle())
                .authorId(article.getAuthorId())
                .date(time)
                .hits(0)
                .contents(article.getContents())
                .build();
    }
}
