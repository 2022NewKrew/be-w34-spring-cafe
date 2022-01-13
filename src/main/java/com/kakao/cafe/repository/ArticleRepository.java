package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {

    List<Article> findAll();
    Optional<Article> findById(int id);
    int save(Article article);

}
