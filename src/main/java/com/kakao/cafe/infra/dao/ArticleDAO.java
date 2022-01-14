package com.kakao.cafe.infra.dao;

import com.kakao.cafe.web.article.form.ArticleInventoryInfo;
import com.kakao.cafe.web.article.form.ArticlePostInfo;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    void saveArticle(ArticleCreateCommand article);

    List<ArticleInventoryInfo> findArticlesWithoutContents();

    Optional<ArticlePostInfo> findArticlePostInfo(Long articleId);
}
