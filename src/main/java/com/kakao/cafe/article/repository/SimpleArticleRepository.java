package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class SimpleArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public Optional<Article> save(Article article) {
        article.setId((long) (articles.size() + 1));
        articles.add(article);
        return Optional.of(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(articles.get(Math.toIntExact(id)));
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }
}
