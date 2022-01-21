package com.kakao.cafe.service.article;

import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ArticleResponse;
import com.kakao.cafe.dto.article.ArticleUpdateDto;

import java.util.List;

public interface ArticleService {
    void addArticle(ArticleRequest articleRequest);
    List<ArticleResponse> findArticles();
    ArticleResponse findArticleById(Long articleId);
    void modifyArticle(ArticleUpdateDto articleUpdateDto, Boolean removal);
}
