package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.ArticleUpdateCommand;
import com.kakao.cafe.article.application.port.in.ArticleUpdateUseCase;
import com.kakao.cafe.article.application.port.out.LoadUpdateInfoPort;
import com.kakao.cafe.article.domain.ArticleUpdate;
import com.kakao.cafe.common.exception.ArticleUpdateException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ArticleUpdateService implements ArticleUpdateUseCase {
    private final LoadUpdateInfoPort loadUpdateInfoPort;

    @Override
    public ArticleUpdateCommand updateArticle(Long articleId, Long userId) throws ArticleUpdateException {
        Optional<String> nicknameByUserIdOptional = loadUpdateInfoPort.findUserNicknameByUserId(userId);
        String nicknameByUserId = nicknameByUserIdOptional.orElseThrow(ArticleUpdateException::new);
        Optional<ArticleUpdate> articleUpdateCommandOptional = loadUpdateInfoPort.findArticleUpdateByArticleId(articleId);
        ArticleUpdate articleUpdate = articleUpdateCommandOptional.orElseThrow(ArticleUpdateException::new);
        articleUpdate.validateUpdateAuth(nicknameByUserId);
        return new ArticleUpdateCommand(
                articleUpdate.getId(),
                articleUpdate.getWriterName(),
                articleUpdate.getTitle(),
                articleUpdate.getContents()
        );
    }

    @Override
    public void putUpdatedArticle(Long articleId, String title, String writerName, String contents) {
        loadUpdateInfoPort.updateArticle(articleId, title, writerName, contents);
    }
}
