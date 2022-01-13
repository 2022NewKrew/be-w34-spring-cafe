package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private Map<Long, Article> articleMap = new TreeMap<>();
    private AtomicLong key = new AtomicLong(1L);

    @Override
    public Long insertArticle(Article article) {
        long currentId = key.getAndIncrement();
        article.updateId(currentId);
        articleMap.put(currentId, article);
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleMap.values());
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(articleMap.getOrDefault(articleId, null));
    }
}
