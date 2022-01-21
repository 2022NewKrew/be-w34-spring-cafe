package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.ArrayList;
import java.util.List;

public class SimpleArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    @Override
    public void save(Article article) {
        synchronized (articles) {
            article.setId((long) (articles.size() + 1));
            articles.add(article);
        }
    }

    @Override
    public Article findById(Long id) {
        return articles.get(Math.toIntExact(id - 1));
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public void update(Long id, Article article) {
    }

    @Override
    public void delete(Long id) {
    }
}
