package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleMemoryRepository implements ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    @Override
    public void save(String title, String content) {
        int id = articles.size() + 1;
        Article article = new Article(id, title, content);
        articles.add(article);
    }

    @Override
    public Article findArticleById(int id) {
        if (id < 1 || id > articles.size()) return null;
        return articles.get(id - 1);
    }

    @Override
    public List<Article> findAllArticles() {
        return articles;
    }
}
