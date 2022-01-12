package com.kakao.cafe.domain.article;

import java.util.List;
import java.util.Optional;

public interface ArticlePort {
    void save(ArticleVo article);

    List<Article> findAll();

    Optional<Article> findById(int index);
}
