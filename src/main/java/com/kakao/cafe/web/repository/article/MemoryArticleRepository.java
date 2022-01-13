package com.kakao.cafe.web.repository.article;

import com.kakao.cafe.web.domain.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryArticleRepository implements ArticleRepository {

    private static List<Article> articles = new ArrayList<>();
    private static int sequence = 0;

    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        articles.add(article);
        return article;
    }

    @Override
    public Optional<Article> findById(int id) {
        return Optional.ofNullable(articles.get(id - 1));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public void clearStore() {
        articles.clear();
    }
}
