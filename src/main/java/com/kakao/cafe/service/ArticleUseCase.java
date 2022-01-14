package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.FindArticleDto;
import java.util.List;
import java.util.Optional;

public interface ArticleUseCase {

    void createArticle(CreateArticleDto createArticleDto);

    Optional<Article> getArticleById(FindArticleDto findArticleDto);

    List<Article> getAll();

}
