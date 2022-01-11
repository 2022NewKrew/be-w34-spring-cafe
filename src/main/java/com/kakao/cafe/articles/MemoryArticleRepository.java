package com.kakao.cafe.articles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryArticleRepository implements ArticleRepository {
    private final List<Article> articles;

    public MemoryArticleRepository() {
        articles = new ArrayList<>();
    }

    @Override
    public Article save(Article article) {
        articles.add(article);

        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                return Optional.of(article);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public int getSize() {
        return articles.size();
    }
}
