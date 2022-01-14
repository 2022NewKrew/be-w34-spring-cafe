package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {
    Article save(Article article);
    Optional<List<Article>> findAll();
    Article findById(String articleId);
}
