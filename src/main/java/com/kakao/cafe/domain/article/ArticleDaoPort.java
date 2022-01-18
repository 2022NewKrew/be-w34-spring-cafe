package com.kakao.cafe.domain.article;

import java.util.List;
import java.util.Optional;

public interface ArticleDaoPort {
    List<Article> findAll();
    Optional<Article> findById(int index);
    void save(ArticleVo article);
}
