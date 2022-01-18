package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.NotFoundException;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);
    List<Article> findAll();
    Article findById(Long id) throws NotFoundException;
    void deleteById(Long id);
}
