package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class MemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public void save(Article article) {
        article.setArticleId(++sequence);
        store.put(article.getArticleId(), article);
    }

    @Override
    public Optional<Article> findArticle(Long articleId) {
        return Optional.ofNullable(store.get(articleId));
    }

    @Override
    public List<Article> findAllArticle() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteArticle(Long articleId) {
        store.remove(articleId);
    }

    @Override
    public void deleteAllArticle() {
        store.clear();
    }
}
