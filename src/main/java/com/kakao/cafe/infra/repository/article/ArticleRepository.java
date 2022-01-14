package com.kakao.cafe.infra.repository.article;

import com.kakao.cafe.infra.dao.ArticleCreateCommand;
import com.kakao.cafe.web.article.form.ArticleInventoryInfo;
import com.kakao.cafe.web.article.form.ArticlePostInfo;

import java.util.List;

public interface ArticleRepository {
    void saveArticle(ArticleCreateCommand articleCreateCommand);

    List<ArticleInventoryInfo> getArticleInventoryInfoList();

    ArticlePostInfo findArticlePostInfo(Long articleId);
}
