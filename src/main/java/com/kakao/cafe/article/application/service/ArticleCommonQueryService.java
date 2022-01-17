package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.ArticleCommonQueryUserCase;
import com.kakao.cafe.article.application.port.in.ArticleInventoryInfo;
import com.kakao.cafe.article.application.port.in.ArticlePostInfo;
import com.kakao.cafe.article.application.port.out.ArticleCommonQueryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleCommonQueryService implements ArticleCommonQueryUserCase {
    private final ArticleCommonQueryPort articleCommonQueryPort;

    @Override
    public List<ArticleInventoryInfo> getArticleInventoryInfoList() {
        return articleCommonQueryPort.getArticleInventoryInfoList();
    }

    @Override
    public ArticlePostInfo findArticlePostInfo(Long articleId) {
        return articleCommonQueryPort.findArticlePostInfo(articleId);
    }
}
