package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private static final Map<Long, Article> articleStore = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public void insert(Article article) {
        article.setArticleId(++sequence);
        articleStore.put(article.getArticleId(), article);
    }

    @Override
    public Optional<Article> selectByArticleId(Long id) {
        return Optional.ofNullable(articleStore.get(id));
    }

    @Override
    public List<Article> selectAll() {
        return new ArrayList<>(articleStore.values());
    }

    // Jdbc로 변환하면서 추가된 메서드
    @Override
    public void update(Article article) {
    }
}
