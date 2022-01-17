package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;

import java.util.Optional;

public interface ArticleRepository {
    public Long save(Article entity);

    public Optional<Article> findById(Long articleId);

    public Page<Article> findAll(Pageable pageable);
}
