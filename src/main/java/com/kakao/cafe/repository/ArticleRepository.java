package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.FindArticleDto;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void create(CreateArticleDto createArticleDto);

    Optional<Article> findById(FindArticleDto findArticleDto);

    List<Article> getAll();

}
