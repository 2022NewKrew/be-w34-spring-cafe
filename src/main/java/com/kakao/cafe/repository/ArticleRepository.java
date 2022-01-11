package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepository implements MyRepository<Article, Long> {

    private final List<Article> articles = new CopyOnWriteArrayList<>();
    private final AtomicLong sequenceId = new AtomicLong();

    @Override
    public Optional<Article> findById(Long id) {
        return articles.stream()
                .filter(a -> a.isEqualUserId(id))
                .findFirst();
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public void save(Article entity) {
        entity.setId(sequenceId.incrementAndGet());
        articles.add(entity);
    }

    @Override
    public void update(Article entity) {
        int index = articles.indexOf(entity);
        articles.set(index, entity);
    }
}
