package com.kakao.cafe.domain.article.repository;

import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    ArticleRowDataDto save(ArticleRowDataDto article);

    Optional<ArticleRowDataDto> findById(Long id);

    Optional<ArticleRowDataDto> findByWriter(String writer);

    List<ArticleRowDataDto> findAll();

}
