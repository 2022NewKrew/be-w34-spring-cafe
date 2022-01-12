package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    Article save(Article article);

    List<Article> findAll();

    Optional<Article> findById(Long id);
}
