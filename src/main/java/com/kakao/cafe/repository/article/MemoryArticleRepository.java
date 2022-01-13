package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private static final Map<Long, Article> articleStore = new HashMap<>();


    @Override
    public void save(Article article) {
        articleStore.put(article.getArticleId(), article);
    }

    @Override
    public Optional<Article> findByArticleId(Long id) {
        return Optional.ofNullable(articleStore.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleStore.values());
    }
}
