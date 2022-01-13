package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.ArrayList;

public interface ArticleRepository {

    Article find(Long id);

    ArrayList<Article> findAll();

    Long persist(ArticleCreateRequestDTO dto);

    void increaseHit(Long id);

}
