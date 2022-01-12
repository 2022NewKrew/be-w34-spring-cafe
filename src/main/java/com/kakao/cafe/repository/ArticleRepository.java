package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.List;

public interface ArticleRepository {
    public Long save(Article article);
    public List<Article> findAll();
    public Article findById(Long postId);
}
