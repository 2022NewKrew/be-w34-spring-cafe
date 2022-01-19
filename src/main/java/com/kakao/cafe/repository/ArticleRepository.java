package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;

import java.util.Optional;

public interface ArticleRepository {
    void save(Article entity);

    Optional<Article> findById(Long articleId);

    Page<Article> findAll(Pageable pageable);
}
