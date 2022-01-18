package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;

import java.util.List;
import java.util.Optional;

public class JdbcArticleRepository implements Repository<Article, String> {

    @Override
    public void create(Article entity) {

    }

    @Override
    public List<Article> readAll() {
        return null;
    }

    @Override
    public Optional<Article> readById(String id) {
        return Optional.empty();
    }
}
