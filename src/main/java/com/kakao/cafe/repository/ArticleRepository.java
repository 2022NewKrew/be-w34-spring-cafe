package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleRepository {
    int save(Article article) throws SQLException;
    void update(Article article);
    List<Article> findAll();
    Article findById(int i);
}
