package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;

import java.util.List;

public interface ArticleRepository {
    void create(ArticleCreateRequestDto requestDto);
    List<Article> findNotDeleted();
    Article findById(Long id);
    void shiftIsDeleted(Long id);
}
