package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.ArticleCommonQueryUserCase;
import com.kakao.cafe.article.application.port.in.ArticleInventoryInfo;
import com.kakao.cafe.article.application.port.in.ArticlePostInfo;
import com.kakao.cafe.article.application.port.in.CommentResponse;
import com.kakao.cafe.article.application.port.out.ArticleCommonQueryPort;
import com.kakao.cafe.article.application.port.out.CommentCommonQueryPort;

import java.util.List;

public class ArticleCommonQueryService implements ArticleCommonQueryUserCase {
    private final ArticleCommonQueryPort articleCommonQueryPort;
    private final CommentCommonQueryPort commentCommonQueryPort;

    public ArticleCommonQueryService(ArticleCommonQueryPort articleCommonQueryPort, CommentCommonQueryPort commentCommonQueryPort) {
        this.articleCommonQueryPort = articleCommonQueryPort;
        this.commentCommonQueryPort = commentCommonQueryPort;
    }

    @Override
    public List<ArticleInventoryInfo> getArticleInventoryInfoList() {
        return articleCommonQueryPort.getArticleInventoryInfoList();
    }

    @Override
    public ArticlePostInfo findArticlePostInfo(Long articleId) {
        return articleCommonQueryPort.findArticlePostInfo(articleId);
    }

    @Override
    public List<CommentResponse> findCommentsByArticleId(Long articleId) {
        return commentCommonQueryPort.findCommentsByArticleId(articleId);
    }
}
