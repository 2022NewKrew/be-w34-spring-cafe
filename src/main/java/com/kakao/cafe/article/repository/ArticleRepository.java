package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {

    Article save(QuestionDTO article);

    Optional<Article> findById(Long id);

    List<Article> findAll();
}
