package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final Map<Long, Article> articles;

    public ArticleRepositoryImpl() {
        this.articles = new HashMap<>();
    }

    @Override
    public void add(Article article) {
        articles.put(article.getId(), article);
    }

    @Override
    public List<Article> findAllArticles() {
        return articles.values().stream()
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }
}
