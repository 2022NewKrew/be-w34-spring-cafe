package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDAOInterface {
    void insert(Article article);

    public List<Article> findAll();

    public Article findById(long id);
}
