package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.vo.Article;

import java.util.List;

public interface ArticleService {
    void write(ArticleRegistrationDto articleDto);

    List<Article> getArticles();

    Article findByArticleId(Integer articleId);
}
