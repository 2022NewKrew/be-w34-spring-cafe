package com.kakao.cafe.service.article;

import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.dto.article.ArticleResDto;

import java.util.List;

public interface ArticleService {
    void addPost(ArticleReqDto articleReqDto);
    List<ArticleResDto> findArticles();
    ArticleResDto findByArticleId(Long articleId);
}
