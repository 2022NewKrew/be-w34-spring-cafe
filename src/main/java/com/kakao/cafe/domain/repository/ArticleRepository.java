package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article create(Draft draft);
    List<Article> list();
    Optional<Article> getById(long id);
}
