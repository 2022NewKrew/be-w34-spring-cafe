package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;

import java.util.List;

public interface ArticleRepository {

    void addArticle(SampleArticleForm form);
    Article findArticle(int articleID);
    List<Article> getArticles();
}
