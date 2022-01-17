package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.repository.ArticleCreateRequestDTO;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleRepository {

    Long persist(ArticleCreateRequestDTO dto);

    Optional<Article> find(Long id);

    ArrayList<Article> findAll();

    void increaseHit(Long id);


}
