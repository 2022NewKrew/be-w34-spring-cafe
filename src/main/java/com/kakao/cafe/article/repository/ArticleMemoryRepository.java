package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


@Repository
@RequiredArgsConstructor
public class ArticleMemoryRepository implements ArticleRepository {

    private final static Map<Long, Article> articleMap = new TreeMap<>();

    @Override
    public void save(Article article) {
        article.setArticleId(articleMap.size() + 1L);
        articleMap.put(articleMap.size() + 1L, article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleMap.get(id));
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleMap.values());
    }
}
