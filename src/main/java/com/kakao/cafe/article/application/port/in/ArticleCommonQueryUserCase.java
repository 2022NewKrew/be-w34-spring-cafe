package com.kakao.cafe.article.application.port.in;

import java.util.List;

public interface ArticleCommonQueryUserCase {
    List<ArticleInventoryInfo> getArticleInventoryInfoList();

    ArticlePostInfo findArticlePostInfo(Long articleId);
}
