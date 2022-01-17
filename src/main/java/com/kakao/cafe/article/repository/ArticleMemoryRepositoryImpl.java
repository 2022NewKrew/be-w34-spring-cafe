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
    private static AtomicLong idSequence = new AtomicLong();

    private final static HashMap<Long, Article> articleDB = new HashMap<>();

    @Override
    public Optional<Article> find(Long id) {
        return Optional.ofNullable(articleDB.get(id));
    }

    public ArrayList<Article> findAll() {
        return new ArrayList<>(articleDB.values());
    }

    public Long persist(ArticleCreateRequestDTO dto) {
        Article article = Article.builder()
                .id(idSequence.get())
                .title(dto.title)
                .authorId(dto.authorId)
                .date(LocalDateTime.now())
                .contents(dto.contents)
                .build();

        articleDB.put(idSequence.get(), article);
        return idSequence.getAndIncrement();
    }

    public void increaseHit(Long id) {
        Article article = articleDB.get(id);
        article.increaseHit();
        articleDB.put(id, article);
    }
}
