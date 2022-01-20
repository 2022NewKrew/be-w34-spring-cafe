package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.ArticleUpdateForm;
import com.kakao.cafe.article.application.port.in.ArticleUpdateUseCase;
import com.kakao.cafe.article.application.port.out.LoadUpdateInfoPort;
import com.kakao.cafe.article.domain.ArticleUpdate;
import com.kakao.cafe.common.exception.ArticleUpdateException;

public class ArticleUpdateService implements ArticleUpdateUseCase {
    private final LoadUpdateInfoPort loadUpdateInfoPort;

    public ArticleUpdateService(LoadUpdateInfoPort loadUpdateInfoPort) {
        this.loadUpdateInfoPort = loadUpdateInfoPort;
    }

    @Override
    public ArticleUpdateForm findArticleUpdateForm(Long articleId, Long userId) throws ArticleUpdateException {
        String nicknameByUserId = loadUpdateInfoPort.findUserIdByArticleId(articleId).orElseThrow(ArticleUpdateException::new);
        ArticleUpdate articleUpdate = loadUpdateInfoPort.findArticleUpdateByArticleId(articleId).orElseThrow(ArticleUpdateException::new);
        articleUpdate.validateUpdateAuth(nicknameByUserId);
        return new ArticleUpdateForm(
                articleUpdate.getId(),
                articleUpdate.getWriterName(),
                articleUpdate.getTitle(),
                articleUpdate.getContents()
        );
    }

    @Override
    public void updateArticle(Long articleId, String title, String writerName, String contents) {
        loadUpdateInfoPort.updateArticle(articleId, title, writerName, contents);
    }
}
