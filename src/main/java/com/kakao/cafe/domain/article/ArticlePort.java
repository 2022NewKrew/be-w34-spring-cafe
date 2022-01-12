package com.kakao.cafe.domain.article;

import java.util.List;

public interface ArticlePort {
    void save(ArticleVo article);

    List<Article> findAll();
}
