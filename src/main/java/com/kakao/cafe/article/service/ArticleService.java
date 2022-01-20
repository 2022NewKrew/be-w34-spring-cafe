package com.kakao.cafe.article.service;

import java.util.List;

import com.kakao.cafe.article.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.article.dto.request.ArticleUpdateRequestDTO;
import com.kakao.cafe.article.dto.response.ArticleFindResponseDTO;

public interface ArticleService {
    void create(ArticleCreateRequestDTO articleCreateRequestDTO);

    List<ArticleFindResponseDTO> getAllArticle();

    ArticleFindResponseDTO getArticleById(int id);

    void update(int id, ArticleUpdateRequestDTO articleUpdateRequestDTO);

    void remove(int id);
}
